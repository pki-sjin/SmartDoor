package org.zero.servlet.web.mms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.zero.db.entity.user.SdUser;

import sun.misc.BASE64Decoder;

import com.lxt2.protocol.common.Standard_SeqNum;
import com.wxtl.smszd.SendSmsZD;

public class SendQRCode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SendQRCode() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String imgBase64 = request.getParameter("imgBase64");

		JSONObject json = new JSONObject();

		SdUser user = (SdUser) request.getSession().getAttribute("USER");
		if (user != null && imgBase64 != null) {
			String cell = user.getName();
			String head = "data:image/png;base64,";
			byte[] data = new BASE64Decoder().decodeBuffer(imgBase64
					.substring(imgBase64.indexOf(head) + head.length()));
			boolean result = SendSmsZD.sendMms(cell, data, 1, Standard_SeqNum
					.computeSeqNoErr(1));
			if (result) {
				json.put("status", "1");
				json.put("data", "发送成功");
			} else {
				json.put("status", "0");
				json.put("data", "发送失败，请联系客服");
			}
		} else {
			json.put("status", "0");
			json.put("data", "请求错误");
		}

		out.print(json);
		out.flush();
		out.close();
	}

}
