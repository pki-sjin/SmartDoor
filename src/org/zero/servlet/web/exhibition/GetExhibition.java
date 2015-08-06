package org.zero.servlet.web.exhibition;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zero.db.entity.exhibition.SdExhibition;
import org.zero.db.entity.exhibition.SdExhibitionDAO;

public class GetExhibition extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetExhibition() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		SdExhibitionDAO dao = new SdExhibitionDAO();
		List<SdExhibition> list = dao.findAll();
		JSONArray array = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		for (SdExhibition ex : list) {
			JSONObject json_ex = new JSONObject();
			json_ex.put("id",ex.getSdExId());
			json_ex.put("subject",ex.getSdExSubject());
			json_ex.put("url",ex.getSdExHtml());
			json_ex.put("start",sdf.format(new Date(ex.getSdExStart().getTime())));
			json_ex.put("end",sdf.format(new Date(ex.getSdExEnd().getTime())));
			array.put(json_ex);
		}
		json.put("list", array);
		out.print(json);
		out.flush();
		out.close();
	}

}
