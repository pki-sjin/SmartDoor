package org.zero.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (authorize(req)) {
			chain.doFilter(req, resp);
		} else {
			resp.sendRedirect("../index.html");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}

	private boolean authorize(HttpServletRequest request) {
		Object o = request.getSession().getAttribute("USER");
		return true;
//		return o != null;
	}
}
