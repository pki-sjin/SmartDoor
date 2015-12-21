package org.zero.servlet.web.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zero.db.entity.activity.SdExUserRelation;
import org.zero.db.entity.activity.SdExUserRelationDAO;
import org.zero.db.entity.exhibition.SdExhibition;
import org.zero.db.entity.exhibition.SdExhibitionDAO;
import org.zero.db.entity.user.SdUser;
import org.zero.db.session.HibernateSessionFactory;

@WebServlet("/GetActivities")
public class GetActivities extends HttpServlet {

	private int ex_id;

	/**
	 * Constructor of the object.
	 */
	public GetActivities() {
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
		JSONObject json = new JSONObject();

		SdUser user = (SdUser) request.getSession().getAttribute("USER");
		SdExhibitionDAO exDao = new SdExhibitionDAO();
		SdExUserRelationDAO relationDao = new SdExUserRelationDAO();
		HibernateSessionFactory.getSession().clear();
		SdExhibition ex = exDao.findById(ex_id);
		JSONArray array = new JSONArray();
		long now = System.currentTimeMillis();

		JSONObject json_ex = new JSONObject();
		json_ex.put("id", ex.getId());
		json_ex.put("subject", ex.getSubject());
		json_ex.put("url", ex.getHtml());
		long start = ex.getStart().getTime();
		long end = ex.getEnd().getTime();
		SdExUserRelation relation = null;
		List relations = relationDao.findByUserId(user.getId());
		if (relations.isEmpty()) {
			// 该用户没有等级过任何的展会，需要初始化
			relation = new SdExUserRelation();
			relation.setExId(ex_id);
			relation.setUserId(user.getId());
			if (start > now) {
				// 可以参加
				relation.setJoinId(1);
			} else if (end > now) {
				// 展会进行中
				relation.setJoinId(5);
			} else {
				// 展会结束
				relation.setJoinId(6);
			}
			relation.setOrderId(0L);

			Transaction transaction = relationDao.getSession()
					.beginTransaction();
			relationDao.save(relation);
			transaction.commit();
		} else {
			relation = (SdExUserRelation) relations.get(0);
			int join_id = relation.getJoinId();
			if (join_id == 1 || join_id == 2 || join_id == 3 || join_id == 5) {
				if (start < now && end > now && join_id != 3 && join_id != 5) {
					// 展会进行中
					Transaction transaction = relationDao.getSession()
							.beginTransaction();
					relation.setJoinId(5);
					relationDao.attachDirty(relation);
					transaction.commit();
				} else if (end < now) {
					// 展会结束
					Transaction transaction = relationDao.getSession()
							.beginTransaction();
					relation.setJoinId(6);
					relationDao.attachDirty(relation);
					transaction.commit();
				}
			}
		}

		json_ex.put("join_id", relation.getJoinId());
		json_ex.put("order_id", relation.getOrderId());

		array.put(json_ex);
		json.put("list", array);
		out.print(json);
		out.flush();
		out.close();
	}
}
