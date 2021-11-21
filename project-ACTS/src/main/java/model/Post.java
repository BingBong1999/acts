package model;

import java.util.Date;

/**
 * 寃뚯떆湲� 愿�由щ�� �쐞�빐 �븘�슂�븳 �룄硫붿씤 �겢�옒�뒪. POST �뀒�씠釉붽낵 ���쓳�맖
 */
public class Post {
	private int postId;
	private String title;
	private String desc;
	private String imgUrl;
	private Date cTime;
	private int categoryId;
	private int views;
	private String status;
	private int price;
	private String pType;
	private int writerId;
	private String thumbImg; //썸네일 이미지
	
	public Post() {
	} // 湲곕낯 �깮�꽦�옄

	public Post(int postId, String title, String desc, String imgUrl, Date cTime, int categoryId, int views, String status, int price, String pType, int writerId) {
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

	// List 異쒕젰�쓣 �쐞�븳 �깮�꽦�옄
	public Post(int postId, String title, int views, String status, int price, String pType, int writerId) {
		super();
		this.postId = postId;
		this.title = title;
		this.views = views;
		this.status = status;
		this.price = price;
		this.pType = pType;
		this.writerId = writerId;
	}

	
	//post 업로드
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
	
//	UpdatePostController parameter생성자
	public Post(int postId, String title, String desc, String imgUrl, int categoryId, String status, int price, String pType) {
		this.postId = postId;
		this.title = title;
		this.desc = desc;
		this.imgUrl = imgUrl;
		this.categoryId = categoryId;
		this.status = status;
		this.price = price;
		this.pType = pType;
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
