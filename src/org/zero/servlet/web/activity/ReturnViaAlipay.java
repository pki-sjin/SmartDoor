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

public class ReturnViaAlipay extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReturnViaAlipay() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 获取支付宝GET过来反馈信息
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
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
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

		// 计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);

		if (verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				SdOrderDAO dao = new SdOrderDAO();
				SdOrder order = dao.findById(Long.parseLong(out_trade_no));
				if (order.getState() == 0) {
					Transaction transaction = dao.getSession()
							.beginTransaction();
					order.setState(1);
					order.setRemark("支付宝订单交易完成前端通知，交易号：" + trade_no);
					order.setFinish(new Timestamp(System.currentTimeMillis()));
					dao.attachDirty(order);
					transaction.commit();
				}
			}
			SdExUserRelationDAO relationDao = new SdExUserRelationDAO();
			List<SdExUserRelation> relations = relationDao.findByOrderId(Long
					.parseLong(out_trade_no));

			if (!relations.isEmpty()) {
				SdExUserRelation relation = relations.get(0);
				relation.setJoinId(3);
				Transaction relationTransaction = relationDao.getSession()
						.beginTransaction();
				relationDao.attachDirty(relation);
				relationTransaction.commit();
			}

			// 该页面可做页面美工编辑
			// 跳转到显示成功页
			response.sendRedirect("feature/invoice.html");
			out.println("验证成功");
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {
			// 该页面可做页面美工编辑
			out.println("验证失败");
		}
		out.flush();
		out.close();
	}

}
