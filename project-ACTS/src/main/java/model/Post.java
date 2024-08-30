package model;

import java.util.Date;
import java.util.List;

public class Post {

	private int id;
	private String title;
	private String body;
	private Date createdAt;
	private int categoryId;
	private String categoryName;
	private int viewCount;
	private String status;
	private int price;
	private String authorId;
	private List<String> imageUrl;

	public Post(int id, String title, String body, Date createdAt, int categoryId, int viewCount, String status,
			int price, String authorId) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.createdAt = createdAt;
		this.categoryId = categoryId;
		this.viewCount = viewCount;
		this.status = status;
		this.price = price;
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public List<String> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", body=" + body + ", createdAt=" + createdAt + ", categoryId="
				+ categoryId + ", categoryName=" + categoryName + ", viewCount=" + viewCount + ", status=" + status
				+ ", price=" + price + ", authorId=" + authorId + ", imageUrl=" + imageUrl + "]";
	}

}