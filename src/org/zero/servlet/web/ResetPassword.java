package org.zero.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

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

public class ResetPassword extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ResetPassword() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sendCode = request.getParameter("code");
		int sms_id = Integer.parseInt(request.getParameter("sms_id"));
		JSONObject json = new JSONObject();

		SdValidationDAO validationDao = new SdValidationDAO();
		SdUserDAO userDao = new SdUserDAO();
		SdValidation validation = null;
		if (sms_id != 0) {
			validation = validationDao.findById(sms_id);
		} else {
			List<SdValidation> list = validationDao.findByCode(sendCode);
			for (SdValidation v : list) {
				if (v.getCell().equalsIgnoreCase(username)) {
					validation = v;
					break;
				}
			}
		}
		if (validation != null) {
			Timestamp create = validation.getCreateTime();
			Timestamp now = new Timestamp(
					System.currentTimeMillis() - 30 * 60 * 1000);
			if (validation.getCode().equalsIgnoreCase(sendCode)
					&& validation.getCell().equalsIgnoreCase(username)
					&& now.compareTo(create) < 0) {
				// validation ok
				List<SdUser> users = userDao.findByName(username);
				if (users.isEmpty()) {
					json.put("status", 0);
					json.put("data", "用户不存在");
				} else {
					SdUser user = users.get(0);
					user.setPassword(Util.encrypt(password));
					Transaction transaction = userDao.getSession()
							.beginTransaction();
					userDao.attachDirty(user);
					transaction.commit();
					request.getSession().setAttribute("USER", user);
					json.put("status", 1);
					json.put("data", "修改密码成功");
				}
			} else {
				json.put("status", 0);
				json.put("data", "验证码错误或已过期，请重新获取。");
			}
		} else {
			json.put("status", 0);
			json.put("data", "验证码错误或已过期，请重新获取。");
		}
		out.print(json);
		out.flush();
		out.close();
	}

}
