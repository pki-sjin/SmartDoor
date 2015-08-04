package org.zero.servlet.web.user;

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

public class GetUser extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetUser() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		SdUser user = (SdUser)request.getSession().getAttribute("USER");
		
		if (user != null) {
			if (user.needFilloutInfo()) {
				json.put("status", 0);
				json.put("data", "请完善个人资料");
			} else {
				json.put("status", 1);
				json.put("data", "");
			}
			
			json.put("user", user.getJSON());
		} else {
			json.put("status", -1);
			json.put("data", "请登录");
		}
		out.print(json);
		out.flush();
		out.close();
	}
}
