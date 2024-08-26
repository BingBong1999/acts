package server;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chat")
public class ChatServer {

	private static Set<Session> clients = new CopyOnWriteArraySet<>();

	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		for (Session client : clients) {
			if (client.isOpen() && !client.equals(session)) {
				client.getBasicRemote().sendText(message);
			}
		}
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
}
