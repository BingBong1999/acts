package model;

import java.util.Date;

public class Review {
	
	private int reviewId;
	private Date cTime;
	private String content;
	private int score;
	private int reviewerId;
	private int postId;
	private User user;
	private Post post;

	public Review() {
	
	}

	public Review(int reviewId, Date cTime, String content, int score, int reviewerId, int postId) {
		
		this.reviewId = reviewId;
		this.cTime = cTime;
		this.content = content;
		this.score = score;
		this.reviewerId = reviewerId;
		this.postId = postId;
	}

	public Review(String content, int score, int reviewerId, int postId) {
		
		this.content = content;
		this.score = score;
		this.reviewerId = reviewerId;
		this.postId = postId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(int reviewerId) {
		this.reviewerId = reviewerId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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
}