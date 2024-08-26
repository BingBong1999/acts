package model.service;

import java.sql.SQLException;

import java.util.List;

import model.Post;
import model.User;
import model.dao.PostDAO;

public class PostManager {

	private static PostManager post = new PostManager();
	private PostDAO postDAO;

	private PostManager() {
		try {
			postDAO = new PostDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PostManager getInstance() {
		return post;
	}

	public int create(Post post) throws SQLException {
		return postDAO.create(post);
	}

	public int update(Post post) throws SQLException, PostNotFoundException {
		return postDAO.update(post);
	}

	public int remove(int postId) throws SQLException, PostNotFoundException {
		return postDAO.remove(postId);
	}

	public Post findPost(int postId) throws SQLException, PostNotFoundException {

		Post post = postDAO.findPost(postId);

		if (post == null) {
			throw new PostNotFoundException(postId + "는 존재하지 않는  포스트입니다.");
		}

		return post;
	}

	public List<Post> findPostList() throws SQLException, UserNotFoundException {
		List<Post> posts = postDAO.findPostList();

		for (Post post : posts) {
			post.setCategory(findCategoryName(post.getPostId()));
			post.setWriter(findWriterName(post.getWriterId()));
		}
		return posts;
	}

	public List<Post> findPostListUseCategory(String cName) throws SQLException {
		return postDAO.findPostListUseCategory(cName);
	}

	public PostDAO getPostDAO() {
		return this.postDAO;
	}

	public void increasePostView(Post post) throws SQLException {
		postDAO.increasePostView(post);
	}

	public String getPostUserNickName(int userId) throws SQLException {

		return postDAO.findUserNickNameByUserId(userId);
	}

	public String getImgUrl(int postId) throws SQLException {
		Post post = postDAO.findPost(postId);

		return post.getImgUrl();
	}

	public List<Post> findMyPostList(int userId) throws SQLException {
		return postDAO.findMyPostList(userId);
	}

	public String findCategoryName(int categoryId) {
		switch (categoryId) {
		case 0:
			return "디자인";
		case 1:
			return "IT";
		case 3:
			return "문서";
		case 4:
			return "기타";
		default:
			return "기타";
		}
	}
	
	public String findWriterName(int writerId) throws SQLException, UserNotFoundException {
		
		UserManager userManager = UserManager.getInstance();
		
		User user = userManager.findUserByUserId(writerId);
		
		return user.getNickName();
	}
	
	
}