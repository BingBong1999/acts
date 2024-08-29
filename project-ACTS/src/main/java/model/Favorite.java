package model;

public class Favorite {

	private int favorId;
	private int postId;
	private String userId;
	
	public Favorite(int postId, String userId) {
		super();
		this.userId = userId;
		this.postId = postId;
	}

	public int getFavorId() {
		return favorId;
	}

	public void setFavorId(int favorId) {
		this.favorId = favorId;
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

}