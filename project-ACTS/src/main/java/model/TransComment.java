package model;

import java.util.Date;

public class TransComment {

	private int commentId;
	private int transId;
	private int commenterId;
	private Date createTime;
	private String commentContent;

	public TransComment(int commentId, int transId, int commenterId, Date createTime, String commentContent) {
		
		super();
		this.commentId = commentId;
		this.transId = transId;
		this.commenterId = commenterId;
		this.createTime = createTime;
		this.commentContent = commentContent;
	}

	public TransComment(int transId, int commenterId, String commentContent) {
		
		super();
		this.transId = transId;
		this.commenterId = commenterId;
		this.commentContent = commentContent;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getCommenterId() {
		return commenterId;
	}

	public void setCommenterId(int commenterId) {
		this.commenterId = commenterId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
}