package org.zero.servlet.web.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
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

import com.alipay.util.AlipayNotify;

public class NotifyViaAlipay extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NotifyViaAlipay() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println(name + "=" + valueStr);
			params.put(name, valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			SdOrderDAO dao = new SdOrderDAO();
			SdOrder order = dao.findById(Long.parseLong(out_trade_no));
			SdExUserRelationDAO relationDao = new SdExUserRelationDAO();
			List<SdExUserRelation> relations = relationDao.findByOrderId(Long
					.parseLong(out_trade_no));
			Transaction transaction = dao.getSession().beginTransaction();
			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				order.setState(2);
				order.setRemark("支付宝订单交易关闭，超过退款期限。交易号：" + trade_no);
				order.setFinish(new Timestamp(System.currentTimeMillis()));
				// 注意：
				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				order.setState(1);
				order.setRemark("支付宝订单付款成功。交易号：" + trade_no);
				order.setFinish(new Timestamp(System.currentTimeMillis()));
				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
			}

			if (!relations.isEmpty()) {
				SdExUserRelation relation = relations.get(0);
				relation.setJoinId(3);
				Transaction relationTransaction = relationDao.getSession()
						.beginTransaction();
				relationDao.attachDirty(relation);
				relationTransaction.commit();
			}

			dao.attachDirty(order);
			transaction.commit();

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			out.println("success"); // 请不要修改或删除

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			out.println("fail");
		}
		out.flush();
		out.close();
	}
}
