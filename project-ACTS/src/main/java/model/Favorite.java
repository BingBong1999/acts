package model;

public class Favorite {
	
	private int favorId;
	private int userId;
	private int postId;

	public Favorite(int favorId, int userId, int postId) {
		this.favorId = favorId;
		this.userId = userId;
		this.postId = postId;
	}

	public Favorite(int postId, int userId) {
		this.postId = postId;
		this.userId = userId;
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

	public int getFavorId() {
		return favorId;
	}

	public void setFavorId(int favorId) {
		this.favorId = favorId;
	}
}