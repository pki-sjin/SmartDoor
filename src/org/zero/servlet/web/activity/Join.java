package org.zero.servlet.web.activity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.zero.db.entity.user.SdUser;
import org.zero.tool.EncodingHandler;

import com.google.zxing.WriterException;

public class Join extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Join() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int join_id = Integer.parseInt(request.getParameter("join_id"));
		int order_id = Integer.parseInt(request.getParameter("order_id"));
		int size = Integer.parseInt(request.getParameter("size"));
		
		SdUser user = (SdUser) request.getSession().getAttribute("USER");
		String imgBase64 = "data:image/png;base64,";
		try {
			imgBase64 += EncodingHandler.createQRCode(user.getName(), size);
		} catch (WriterException e) {
			// give a error picture
		}
		json.put("qrcode", imgBase64);
		if (join_id == 1) {
			json.put("message", "您已登记参加，凭借上述二维码去现场购票或选择以下支付方式在线支付。");
		} else if (join_id == 2) {
			json.put("message", "您已登记参加，凭借上述二维码去现场购票或选择以下支付方式在线支付。");
		} else if (join_id == 3) {
			json.put("message", "您已成功购买门票，凭借上述二维码可直接去现场取票入场。");
		} else if (join_id == 4) {
			json.put("message", "您已成功参加展会。");
		} else if (join_id == 5) {
			json.put("message", "展会进行中，凭借上述二维码去现场购票。");
		} else if (join_id == 6) {
			json.put("message", "展会已经结束。");
		}

		out.print(json);
		out.flush();
		out.close();
	}

}
