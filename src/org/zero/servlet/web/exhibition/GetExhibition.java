package org.zero.servlet.web.exhibition;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zero.db.entity.exhibition.SdExhibition;
import org.zero.db.entity.exhibition.SdExhibitionDAO;

@WebServlet("/GetExhibition")
public class GetExhibition extends HttpServlet {

	private int ex_id;

	/**
	 * Constructor of the object.
	 */
	public GetExhibition() {
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
		
		SdExhibitionDAO dao = new SdExhibitionDAO();
		SdExhibition ex = dao.findById(ex_id);
		JSONArray array = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

		JSONObject json_ex = new JSONObject();
		json_ex.put("id",ex.getId());
		json_ex.put("subject",ex.getSubject());
		json_ex.put("url",ex.getHtml());
		json_ex.put("start",sdf.format(new Date(ex.getStart().getTime())));
		json_ex.put("end",sdf.format(new Date(ex.getEnd().getTime())));
		array.put(json_ex);
	
		json.put("list", array);
		out.print(json);
		out.flush();
		out.close();
	}
}
