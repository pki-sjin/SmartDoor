package org.zero.servlet.web.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zero.db.entity.order.SdOrder;
import org.zero.db.entity.order.SdOrderDAO;

public class WxPayCheck extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WxPayCheck() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String order_id = request.getParameter("order_id");
		SdOrderDAO dao = new SdOrderDAO();
		SdOrder order = dao.findById(Long.parseLong(order_id));
		out.print(order.getState()+1);
		out.flush();
		out.close();
	}

}
