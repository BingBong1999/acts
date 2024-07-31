package model;

import java.util.Date;

public class Transaction {

	private int transId;
	private Date transDate;
	private User user;
	private Post post;
	private int userId;
	private int postId;
	private String transTitle;
	private String transContents;

	public Transaction(int transId, Date transDate, User user, Post post, String transTitle, String transContents) {
		
		this.transId = transId;
		this.transDate = transDate;
		this.user = user;
		this.post = post;
		this.transTitle = transTitle;
		this.transContents = transContents;
	}

	public Transaction(User user, Post post, String transTitle, String transContents) {
		
		this.user = user;
		this.post = post;
		this.transTitle = transTitle;
		this.transContents = transContents;
	}

	public Transaction(int transId, Date transDate, int userId, int postId, String transTitle, String transContents) {
		
		this.transId = transId;
		this.transDate = transDate;
		this.userId = userId;
		this.postId = postId;
		this.transTitle = transTitle;
		this.transContents = transContents;
	}

	public Transaction(int transId, String transTitle, String transContents) {
		
		this.transId = transId;
		this.transTitle = transTitle;
		this.transContents = transContents;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTransTitle() {
		return transTitle;
	}

	public void setTransTitle(String transTitle) {
		this.transTitle = transTitle;
	}

	public String getTransContents() {
		return transContents;
	}

	public void setTransContents(String transContents) {
		this.transContents = transContents;
	}

	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", transDate=" + transDate + ", user=" + user + ", post=" + post
				+ ", userId=" + userId + ", postId=" + postId + ", transTitle=" + transTitle + ", transContents="
				+ transContents + "]";
	}
}