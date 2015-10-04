package com.wx.pay.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.xml.sax.SAXException;
import org.zero.db.entity.activity.SdExUserRelation;
import org.zero.db.entity.activity.SdExUserRelationDAO;
import org.zero.db.entity.order.SdOrder;
import org.zero.db.entity.order.SdOrderDAO;

import com.wx.pay.api.WxPayApi;
import com.wx.pay.api.WxPayData;
import com.wx.pay.lib.Notify;
import com.wx.pay.lib.WxPayException;

/// <summary>
/// 支付结果通知回调处理类
/// 负责接收微信支付后台发送的支付结果并对订单有效性进行验证，将验证结果反馈给微信支付后台
/// </summary>
public class ResultNotify extends Notify {

	private static Logger Log = Logger.getLogger(ResultNotify.class);

	public ResultNotify(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void ProcessNotify() throws NoSuchAlgorithmException,
			WxPayException, IOException, ParserConfigurationException,
			SAXException {
		WxPayData notifyData = GetNotifyData();

		// 检查支付结果中transaction_id是否存在
		if (!notifyData.IsSet("transaction_id")) {
			// 若transaction_id不存在，则立即返回结果给微信支付后台
			WxPayData res = new WxPayData();
			res.SetValue("return_code", "FAIL");
			res.SetValue("return_msg", "支付结果中微信订单号不存在");
			Log.error("The Pay result is error : " + res.ToXml());
			PrintWriter print = response.getWriter();
			print.write(res.ToXml());
			print.flush();
			print.close();
		}

		String transaction_id = notifyData.GetValue("transaction_id")
				.toString();

		// 查询订单，判断订单真实性
		if (!QueryOrder(transaction_id)) {
			// 若订单查询失败，则立即返回结果给微信支付后台
			WxPayData res = new WxPayData();
			res.SetValue("return_code", "FAIL");
			res.SetValue("return_msg", "订单查询失败");
			Log.error("Order query failure : " + res.ToXml());
			PrintWriter print = response.getWriter();
			print.write(res.ToXml());
			print.flush();
			print.close();
		}
		// 查询订单成功
		else {
			// 更新数据库状态
			String out_trade_no = notifyData.GetValue("out_trade_no")
					.toString();
			SdOrderDAO dao = new SdOrderDAO();
			SdOrder order = dao.findById(Long.parseLong(out_trade_no));
			if (order.getState() == 0) {
				Transaction transaction = dao.getSession().beginTransaction();
				order.setState(1);
				order.setRemark("微信订单交易完成前端通知，微信交易号：" + transaction_id);
				order.setFinish(new Timestamp(System.currentTimeMillis()));
				dao.attachDirty(order);
				transaction.commit();
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

			WxPayData res = new WxPayData();
			res.SetValue("return_code", "SUCCESS");
			res.SetValue("return_msg", "OK");
			Log.info("order query success : " + res.ToXml());
			PrintWriter print = response.getWriter();
			print.write(res.ToXml());
			print.flush();
			print.close();
		}
	}

	// 查询订单
	private boolean QueryOrder(String transaction_id)
			throws NoSuchAlgorithmException, WxPayException,
			ParserConfigurationException, SAXException, IOException {
		WxPayData req = new WxPayData();
		req.SetValue("transaction_id", transaction_id);
		WxPayData res = WxPayApi.OrderQuery(req, 0);
		if (res.GetValue("return_code").toString().equalsIgnoreCase("SUCCESS")
				&& res.GetValue("result_code").toString().equalsIgnoreCase(
						"SUCCESS")) {
			return true;
		} else {
			return false;
		}
	}

}
