package controller.chat;

import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import model.Message;
import model.service.ChatManager;

@ServerEndpoint("/chatSocket")
public class ChatWebSocket {

	private static Map<String, Session> userSessions = Collections.synchronizedMap(new HashMap<>());
	private ChatManager chatManager = ChatManager.getInstance();

	@OnOpen
	public void onOpen(Session session) {

		String query = session.getQueryString();
		String userId = null;

		if (query != null) {
			for (String param : query.split("&")) {
				String[] keyValue = param.split("=");
				if ("userId".equals(keyValue[0]) && keyValue.length > 1) {
					userId = keyValue[1];
				}
			}
		}

		if (userId != null) {
			if (userSessions.containsKey(userId)) {
				onClose(userSessions.get(userId));
			}

			userSessions.put(userId, session);
			session.getUserProperties().put("userId", userId);
			System.out.println("새로운 클라이언트 연결: " + session.getId() + ", 사용자 ID: " + userId);
		}
	}

	@OnClose
	public void onClose(Session session) {
		String userId = (String) session.getUserProperties().get("userId");

		if (userId != null) {
			userSessions.remove(userId);
			System.out.println("클라이언트 연결 종료: " + session.getId() + ", 사용자 ID: " + userId);
		}
	}

	@OnMessage
	public void onMessage(String messageJson, Session session) {
		System.out.println("메시지 수신: " + messageJson);
		Message message = new Gson().fromJson(messageJson, Message.class);

		try {
			chatManager.createMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sendToSpecificUsers(messageJson, message.getReceiverId(), message.getSenderId());
	}

	private void sendToSpecificUsers(String message, String receiverId, String senderId) {
		synchronized (userSessions) {
			userSessions.forEach((userId, session) -> {
				if (userId.equals(receiverId) || userId.equals(senderId)) {
					try {
						session.getBasicRemote().sendText(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
