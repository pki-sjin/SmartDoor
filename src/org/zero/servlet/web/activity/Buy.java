package org.zero.servlet.web.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;
import org.zero.db.entity.activity.SdExUserRelation;
import org.zero.db.entity.activity.SdExUserRelationDAO;
import org.zero.db.entity.order.SdOrder;
import org.zero.db.entity.order.SdOrderDAO;
import org.zero.db.entity.ticket.SdTicket;
import org.zero.db.entity.ticket.SdTicketDAO;
import org.zero.db.entity.user.SdUser;
import org.zero.tool.EncodingHandler;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.wx.pay.business.JsApiPay;
import com.wx.pay.business.NativePay;

public class Buy extends HttpServlet {

	private int ex_id;

	public Buy() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ex_id = Integer.parseInt(getServletContext().getInitParameter(
				"ActivedExhibitionId"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// generate order and request to pay
		String ticket_id = request.getParameter("tickets-choice");
		String pay_method = request.getParameter("radio-choice-pay");
		String order_id = request.getParameter("order_id");

		SdUser user = (SdUser) request.getSession().getAttribute("USER");
		if (user == null) {
			response.sendRedirect("index.html");
			return;
		}

		SdTicketDAO dao = new SdTicketDAO();
		SdTicket ticket = dao.findById(Integer.parseInt(ticket_id));

		SdOrderDAO orderDao = new SdOrderDAO();

		SdOrder order = new SdOrder(Long.parseLong(order_id),
				ticket.getPrice(), ticket.getId(), 0, pay_method,
				new Timestamp(System.currentTimeMillis()), 0, 1);

		{
			Transaction transaction = orderDao.getSession().beginTransaction();
			orderDao.save(order);
			transaction.commit();
		}

		SdExUserRelation relation = null;
		SdExUserRelationDAO relationDao = new SdExUserRelationDAO();
		List relations = relationDao.findByUserId(user.getId());
		if (relations.isEmpty()) {
			relation = new SdExUserRelation();
			relation.setExId(ex_id);
			relation.setJoinId(2);
			relation.setUserId(user.getId());
			relation.setOrderId(order.getId());

			{
				Transaction transaction = relationDao.getSession()
						.beginTransaction();
				relationDao.save(relation);
				transaction.commit();
			}
		} else {
			relation = (SdExUserRelation) relations.get(0);
			{
				Transaction transaction = relationDao.getSession()
						.beginTransaction();
				relation.setJoinId(2);
				relation.setOrderId(order.getId());
				relationDao.attachDirty(relation);
				transaction.commit();
			}
		}

		if ("alipay".equalsIgnoreCase(pay_method)) {
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("seller_email", AlipayConfig.seller_email);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			// 支付类型
			sParaTemp.put("payment_type", "1");
			// 必填，不能修改
			// 服务器异步通知页面路径
			sParaTemp.put("notify_url", AlipayConfig.notify_url);
			// 页面跳转同步通知页面路径
			sParaTemp.put("return_url", AlipayConfig.return_url);
			// 商户订单号
			sParaTemp.put("out_trade_no", order.getId() + "");
			// 订单名称
			sParaTemp.put("subject", ticket.getName());
			// 付款金额
			sParaTemp.put("total_fee", ticket.getPrice() + "");

			sParaTemp.put("body", "");

			sParaTemp.put("show_url", "");
			// 防钓鱼时间戳
			sParaTemp.put("anti_phishing_key", System.currentTimeMillis() + "");
			// 客户端的IP地址
			sParaTemp.put("exter_invoke_ip", request.getRemoteAddr());

			// 建立请求
			String sHtmlText = AlipaySubmit
					.buildRequest(sParaTemp, "get", "确认");
			out.println(sHtmlText);
			out.flush();
			out.close();
		} else if ("wechatscan".equalsIgnoreCase(pay_method)) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>支付购买</title>");
			out
					.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			out
					.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			out.println("<link rel=\"stylesheet\" href=\"css/feature.css\">");
			out.println("<script src=\"js/jquery-2.1.4.min.js\"></script>");
			out
					.println("<script src=\"js/jquery.mobile-1.4.5.min.js\"></script>");
			out.println("<script>");
			out.println("var interval = setInterval(function(){");
			out.println("$.ajax({");
			out.println("url: \"WxPayCheck\",");
			out.println("type: \"POST\",");
			out.println("data: \"order_id=\" + " + order.getId() + ",");
			out.println("success: function(resp){");
			out.println("console.log(resp);");
			out.println("if(resp == \"1\") {");
			out.println("window.location = \"feature/invoice.html\";");
			out.println("}");
			out.println("},");
			out.println("error: function(resp) {");
			out.println("console.log(resp);");
			out.println("}");
			out.println("});");
			out.println("}, 5000);");
			out.println("</script>");
			out.println("</head>");
			out.println("<body>");
			NativePay nativePay = new NativePay();
			try {
				String url = nativePay.GetPayUrl(order.getId() + "", ticket
						.getId()
						+ "", ticket.getName(), ticket.getDescription(),
						(int) (ticket.getPrice() * 100), ticket.getCode());

				out.println("<div align=\"center\">");
				out.println("<img src=\"data:image/png;base64,"
						+ EncodingHandler.createQRCode(url, 300) + "\"/>");
				out.println("</div>");
				out.println("<div align=\"center\">");
				out.println("请用微信扫一扫以上二维码进行支付。");
				out.println("</div>");

			} catch (Exception e) {
				out.println("<div align=\"center\">");
				out.println(e.getMessage());
				out.println("</div>");
				e.printStackTrace();
			}
			out.println("</body>");
			out.println("</html>");
			out.flush();
			out.close();
		} else if ("wechatjs".equalsIgnoreCase(pay_method)) {
			JsApiPay jsApiPay = new JsApiPay(request, response);
			try {
				jsApiPay.GetOpenidAndAccessToken();
			} catch (Exception e) {
			}
			out.flush();
			out.close();
		}
	}
}
