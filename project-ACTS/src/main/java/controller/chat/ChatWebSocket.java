package controller.chat;

import java.io.IOException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	private ChatManager chatManager = ChatManager.getInstance();

	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		System.out.println("새로운 클라이언트 연결: " + session.getId());
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("클라이언트 연결 종료: " + session.getId());
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
		synchronized (clients) {
			for (Session client : clients) {
				if (client.getUserProperties().get("userId").equals(receiverId) ||
		                client.getUserProperties().get("userId").equals(senderId)) {
					try {
						client.getBasicRemote().sendText(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
