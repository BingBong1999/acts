package model;

public class Message {

	private int id;
	private String senderId;
	private String receiverId;
	private String content;
	private String createdAt;

	public Message(int id, String senderId, String receiverId, String content, String createdAt) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", content=" + content
				+ ", createdAt=" + createdAt + "]";
	}

	
}
