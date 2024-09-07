package model.UserAction;

public class UserActionProcessed {

	private String userId;
	private int postId;
	private int liked;
	private double viewDuration;
	private int viewCount;

	public UserActionProcessed(String userId, int postId, int liked, double viewDuration, int viewCount) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.liked = liked;
		this.viewDuration = viewDuration;
		this.viewCount = viewCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public double getViewDuration() {
		return viewDuration;
	}

	public void setViewDuration(double viewDuration) {
		this.viewDuration = viewDuration;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "UserActionPreprocessData [userId=" + userId + ", postId=" + postId + ", liked=" + liked
				+ ", viewDuration=" + viewDuration + ", viewCount=" + viewCount + "]";
	}

}
