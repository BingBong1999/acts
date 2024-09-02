package model.service;

import java.util.List;

import model.Message;
import model.dao.ChatDAO;

public class ChatManager {

	private static ChatManager chatManager = new ChatManager();
	private ChatDAO chatDAO;

	private ChatManager() {
		chatDAO = new ChatDAO();
	}

	public static ChatManager getInstance() {
		return chatManager;
	}


	public int createMessage(Message message) throws Exception {
		return chatDAO.createMessage(message);
	}

	public List<Message> findLatestMessagesPerReceiverBySenderId(String senderId) throws Exception {
		return chatDAO.findLatestMessagesPerReceiverBySenderId(senderId);
	}
	
	public List<Message> findMessagesBySenderIdAndReceiverId(String senderId, String receiverId) throws Exception {
		return chatDAO.findMessagesBySenderIdAndReceiverId(senderId, receiverId);
	}

}
