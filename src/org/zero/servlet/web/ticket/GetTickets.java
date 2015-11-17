package org.zero.servlet.web.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zero.db.entity.ticket.SdTicket;
import org.zero.db.entity.ticket.SdTicketDAO;
import org.zero.db.entity.user.SdUser;

@WebServlet("/GetTickets")
public class GetTickets extends HttpServlet {

	private int ex_id;

	/**
	 * Constructor of the object.
	 */
	public GetTickets() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ex_id = Integer.parseInt(getServletContext().getInitParameter(
				"ActivedExhibitionId"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		SdUser user = (SdUser) request.getSession().getAttribute("USER");
		JSONObject json = new JSONObject();
		if (user == null) {
			json.put("status", -1);
		} else {
			SdTicketDAO dao = new SdTicketDAO();
			List<SdTicket> tickets = dao.findByExId(ex_id);
			if (tickets.isEmpty()) {
				json.put("status", 0);
				json.put("data", "没有可以购买的票");
			} else {
				json.put("status", 1);
				JSONArray array = new JSONArray();
				for (SdTicket ticket : tickets) {
					JSONObject json_ticket = new JSONObject();
					json_ticket.put("id", ticket.getId());
					json_ticket.put("code", ticket.getCode());
					json_ticket.put("name", ticket.getName());
					json_ticket.put("price", ticket.getPrice() + "元");
					json_ticket.put("description", ticket.getDescription());
					array.put(json_ticket);
				}
				json.put("list", array);
			}
		}
		out.print(json);
		out.flush();
		out.close();
	}

}
