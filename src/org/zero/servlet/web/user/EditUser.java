package org.zero.servlet.web.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;
import org.json.JSONObject;
import org.zero.db.entity.user.SdUser;
import org.zero.db.entity.user.SdUserDAO;

public class EditUser extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditUser() {
		super();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String display = request.getParameter("display");
		String company = request.getParameter("company");
		String position = request.getParameter("position");
		String phone = request.getParameter("phone");
		String cell = request.getParameter("cell");
		String fax = request.getParameter("fax");
		String email = request.getParameter("email");
		
		JSONObject json = new JSONObject();
		SdUser user = (SdUser)request.getSession().getAttribute("USER");
		if (user != null) {
			SdUserDAO dao = new SdUserDAO();
			user.setSdUserDisplayname(display);
			user.setSdUserCompany(company);
			user.setSdUserPosition(position);
			user.setSdUserCell(cell);
			user.setSdUserTel(phone);
			user.setSdUserFax(fax);
			user.setSdUserMail(email);
			Transaction transaction = dao.getSession().beginTransaction();
			dao.attachDirty(user);
			json.put("status", 1);
			json.put("data", "修改成功");
			transaction.commit();
		} else {
			json.put("status", -1);
			json.put("data", "请登录");
		}
		out.print(json);
		out.flush();
		out.close();
	}
}