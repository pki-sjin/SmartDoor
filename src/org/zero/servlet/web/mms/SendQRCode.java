package org.zero.servlet.web.mms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.zero.db.entity.user.SdUser;
import org.zero.tool.EncodingHandler;

import com.google.zxing.WriterException;
import com.lxt2.protocol.common.Standard_SeqNum;
import com.wxtl.smszd.SendSmsZD;

@WebServlet("/SendQRCode")
public class SendQRCode extends HttpServlet {

	private int productID;

	/**
	 * Constructor of the object.
	 */
	public SendQRCode() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		productID = Integer.parseInt(getServletContext().getInitParameter(
				"productID"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();

		JSONObject json = new JSONObject();

		SdUser user = (SdUser) request.getSession().getAttribute("USER");
		if (user != null) {
			try {
				String cell = user.getName();
				byte[] data = EncodingHandler.createQRCode(cell, 200);
				boolean result = SendSmsZD.sendMms(cell, data, productID,
						Standard_SeqNum.computeSeqNoErr(1));
				if (result) {
					json.put("status", "1");
					json.put("data", "发送成功");
				} else {
					json.put("status", "0");
					json.put("data", "发送失败，请联系客服");
				}
			} catch (WriterException e) {
				e.printStackTrace();
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
