package model;

import java.util.Date;

public class Post {
	
	private int postId;
	private String title;
	private String desc;
	private String imgUrl;
	private Date cTime;
	private int categoryId;
	private String category;
	private int views;
	private String status;
	private int price;
	private String pType;
	private int writerId;
	private String writer;
	private String thumbImg;

	public Post() {
	
	}

	public Post(int postId, String title, String desc, String imgUrl, Date cTime, int categoryId, int views,
			String status, int price, String pType, int writerId) {
		
		super();
		this.postId = postId;
		this.title = title;
		this.desc = desc;
		this.imgUrl = imgUrl;
		this.cTime = cTime;
		this.categoryId = categoryId;
		this.views = views;
		this.status = status;
		this.price = price;
		this.pType = pType;
		this.writerId = writerId;
	}

	public Post(int postId, String title, String imgUrl, int views, String status, int price, String pType, int writerId) {
		
		this.postId = postId;
		this.title = title;
		this.imgUrl = imgUrl;
		this.views = views;
		this.status = status;
		this.price = price;
		this.pType = pType;
		this.writerId = writerId;
	}

	public Post(String title, String desc, String imgUrl, int categoryId, String status, int price, String pType, int writerId) {
		
		this.title = title;
		this.desc = desc;
		this.imgUrl = imgUrl;
		this.categoryId = categoryId;
		this.status = status;
		this.price = price;
		this.pType = pType;
		this.writerId = writerId;

	}

	public Post(int postId, String title, String desc, String imgUrl, int categoryId, String status, int price, String pType, int writerId) {
		
		this.postId = postId;
		this.title = title;
		this.desc = desc;
		this.imgUrl = imgUrl;
		this.categoryId = categoryId;
		this.status = status;
		this.price = price;
		this.pType = pType;
		this.writerId = writerId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
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

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", desc=" + desc + ", imgUrl=" + imgUrl + ", cTime="
				+ cTime + ", categoryId=" + categoryId + ", views=" + views + ", status=" + status + ", price=" + price
				+ ", pType=" + pType + ", writerId=" + writerId + ", thumbImg=" + thumbImg + "]";
	}

	
}