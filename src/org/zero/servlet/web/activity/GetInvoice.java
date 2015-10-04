package org.zero.servlet.web.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.zero.db.entity.activity.SdExUserRelation;
import org.zero.db.entity.activity.SdExUserRelationDAO;
import org.zero.db.entity.order.SdOrder;
import org.zero.db.entity.order.SdOrderDAO;
import org.zero.db.entity.ticket.SdTicket;
import org.zero.db.entity.ticket.SdTicketDAO;
import org.zero.db.entity.user.SdUser;

public class GetInvoice extends HttpServlet {

	private int ex_id;

	/**
	 * Constructor of the object.
	 */
	public GetInvoice() {
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
			SdExUserRelationDAO relationDao = new SdExUserRelationDAO();
			List<SdExUserRelation> relations = relationDao.findByUserId(user
					.getId());
			if (!relations.isEmpty()) {
				SdExUserRelation relation = relations.get(0);
				long order_id = relation.getOrderId();
				SdOrderDAO orderDao = new SdOrderDAO();
				SdOrder order = orderDao.findById(order_id);
				if (order != null) {
					int ticket_id = order.getTicket();
					SdTicketDAO ticketDao = new SdTicketDAO();
					SdTicket ticket = ticketDao.findById(ticket_id);
					json.put("status", 1);
					json.put("order", order.getId());
					json.put("price", order.getPrice());
					if (ticket != null) {
						json.put("ticket", ticket.getName());
					}
					json.put("method", order.getMethod());
					json.put("create", order.getCreateTime());
					json.put("finish", order.getFinish());
					json.put("state", order.getState() == 1?"支付成功":"支付失败");
				} else {
					json.put("status", 0);
					json.put("data", "没有找到订单");
				}
			} else {
				json.put("status", 0);
				json.put("data", "没有找到凭证");
			}
		}
		out.print(json);
		out.flush();
		out.close();
	}

}
