package org.zero.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.zero.db.entity.user.SdUser;
import org.zero.db.entity.user.SdUserDAO;
import org.zero.tool.Util;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		JSONObject json = new JSONObject();
		SdUserDAO dao = new SdUserDAO();
		List<SdUser> users = dao.findBySdUserName(username);
		if (users.size() > 0) {
			SdUser user = users.get(0);
			if (user.getSdUserPassword().equalsIgnoreCase(
					Util.encrypt(password))) {
				request.getSession().setAttribute("USER", user);
				json.put("status", 1);
				json.put("data", "success");
			} else {
				// password is not correct
				json.put("status", 0);
				json.put("data", "密码不正确");
			}
		} else {
			// user does not exist
			json.put("status", 0);
			json.put("data", "用户不存在");
		}
		out.print(json);
		out.flush();
		out.close();
	}
}