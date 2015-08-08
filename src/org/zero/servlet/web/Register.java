package org.zero.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;
import org.json.JSONObject;
import org.zero.db.entity.sms.SdValidation;
import org.zero.db.entity.sms.SdValidationDAO;
import org.zero.db.entity.user.SdUser;
import org.zero.db.entity.user.SdUserDAO;
import org.zero.tool.Util;

public class Register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Register() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sendCode = request.getParameter("code");
		String sms_id = request.getParameter("sms_id");

		JSONObject json = new JSONObject();

		SdValidationDAO validationDao = new SdValidationDAO();
		SdUserDAO userDao = new SdUserDAO();
		SdValidation validation = validationDao.findById(Integer
				.parseInt(sms_id));
		Timestamp create = validation.getSdValidationCreate();
		Timestamp now = new Timestamp(
				System.currentTimeMillis() - 30 * 60 * 1000);
		if (sendCode.equalsIgnoreCase(validation.getSdValidationCode())
				&& username.equalsIgnoreCase(validation.getSdValidationCell())
				&& now.compareTo(create) < 0) {
			// validation ok
			SdUser user = new SdUser(username, Util.encrypt(password), "", 0,
					new Timestamp(System.currentTimeMillis()), new Timestamp(
							System.currentTimeMillis()));
			Transaction transaction = userDao.getSession().beginTransaction();
			userDao.save(user);
			transaction.commit();
			request.getSession().setAttribute("USER", user);
			json.put("status", 1);
			json.put("data", "注册成功");
		} else {
			json.put("status", 0);
			json.put("data", "验证码错误或已过期，请重新获取。");
		}
		out.print(json);
		out.flush();
		out.close();
	}
}
