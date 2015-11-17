package org.zero.servlet.web.stage;

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
import org.zero.db.entity.stage.SdStage;
import org.zero.db.entity.stage.SdStageDAO;

@WebServlet("/GetStage")
public class GetStage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetStage() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String ex_id = request.getParameter("ex_id");
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		SdStageDAO stageDao = new SdStageDAO();
		List<SdStage> list = stageDao.findByExId(Integer.parseInt(ex_id));
		for (SdStage stage : list) {
			JSONObject json_ex = new JSONObject();
			json_ex.put("id", stage.getId());
			json_ex.put("subject", stage.getSubject());
			json_ex.put("url", stage.getHtml());
			array.put(json_ex);
		}
		json.put("list", array);
		out.print(json);
		out.flush();
		out.close();
	}
}
