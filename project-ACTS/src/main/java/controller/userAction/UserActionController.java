package controller.userAction;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Controller;
import model.UserAction.UserActionRecord;
import model.service.UserActionManager;

public class UserActionController implements Controller {

	private static UserActionManager userActionManager = UserActionManager.getInstance();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		JSONObject jsonData = new JSONObject(sb.toString());
		String loginId = jsonData.getString("loginId");
		int postId = jsonData.getInt("postId");
		String actionType = jsonData.getString("actionType");
		int duration = jsonData.getInt("duration");

		System.out.println("User ID: " + loginId);
		System.out.println("Post ID: " + postId);
		System.out.println("Action Type: " + actionType);
		System.out.println("Time Spent: " + duration + " seconds");

		UserActionRecord record = new UserActionRecord(loginId, postId, actionType, duration);

		try {
			if (actionType.equals("deleteLiked")) {
				userActionManager.delete(record);
			} else {
				userActionManager.create(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"status\":\"success\"}");
		
		return null;
	}

}
