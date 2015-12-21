package org.zero.servlet.web.activity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.pay.business.ResultNotify;

@WebServlet("/NotifyViaWx")
public class NotifyViaWx extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NotifyViaWx() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ResultNotify resultNotify = new ResultNotify(request, response);
		try {
			resultNotify.ProcessNotify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ResultNotify resultNotify = new ResultNotify(request, response);
		try {
			resultNotify.ProcessNotify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
