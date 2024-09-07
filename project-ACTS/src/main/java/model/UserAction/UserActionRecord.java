package model.UserAction;

public class UserActionRecord {

	private String userId;
	private int postId;
	private String actionType;
	private int duration;

	private int liked;
	private double viewDuration;
	private int viewCount;

	public UserActionRecord(String userId, int postId, String actionType, int duration) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.actionType = actionType;
		this.duration = duration;
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

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
		return "UserAction [userId=" + userId + ", postId=" + postId + ", actionType=" + actionType + ", duration="
				+ duration + ", liked=" + liked + ", viewDuration=" + viewDuration + ", viewCount=" + viewCount + "]";
	}

}
