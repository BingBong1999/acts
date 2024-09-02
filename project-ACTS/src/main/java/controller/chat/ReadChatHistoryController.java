package controller.chat;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Message;
import model.service.ChatManager;

import controller.Controller;
import controller.user.UserSessionUtils;

public class ReadChatHistoryController implements Controller {

	ChatManager chatManager = ChatManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String loginId = UserSessionUtils.getLoginUserId(request.getSession());

		try {

			List<Message> messageList = chatManager.findMessagesBySenderIdAndReceiverId(loginId, request.getParameter("receiverId"));

			Map<String, Object> result = new HashMap<>();
			result.put("loginId", loginId);
            result.put("messageList", messageList);
            
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			String json = new Gson().toJson(result);
			
			PrintWriter out = response.getWriter();
			out.write(json);
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");
		}

		return null;
	}

}
