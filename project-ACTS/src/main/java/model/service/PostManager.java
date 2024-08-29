package model.service;

import java.sql.SQLException;

import java.util.List;

import exception.PostNotFoundException;
import exception.UserNotFoundException;
import model.Category;
import model.Post;
import model.dao.PostDAO;

public class PostManager {

	private static ImageManager imageManager = ImageManager.getInstance();
	private static PostManager post = new PostManager();
	private PostDAO postDAO;

	private PostManager() {
		postDAO = new PostDAO();
	}

	public static PostManager getInstance() {
		return post;
	}

	public int create(Post post, List<String> imageUrls) throws Exception {
		return postDAO.create(post, imageUrls);
	}

	public int update(Post post) throws SQLException, PostNotFoundException {
		return postDAO.update(post);
	}

	public int delete(int id) throws SQLException, PostNotFoundException {
		return postDAO.delete(id);
	}

	public Post findPostByPostId(int id) throws SQLException, PostNotFoundException {

		Post post = postDAO.findPostByPostId(id);
		
		if (post == null) {
			throw new PostNotFoundException(id + "는 존재하지 않는  포스트입니다.");
		}

		post.setImageUrl(imageManager.findImageUrlsByPostId(post.getId()));

		return post;
	}

	public List<Post> findAllPosts() throws SQLException, UserNotFoundException {
		List<Post> posts = postDAO.findAllPosts();

		for (Post post : posts) {
			post.setCategoryName(Category.getNameById(post.getCategoryId()));
			post.setImageUrl(imageManager.findImageUrlsByPostId(post.getId()));
		}
		return posts;
	}

	public List<Post> findPostsByCategory(int category) throws SQLException {
		return postDAO.findPostsByCategory(category);
	}


	public void increaseViewCount(Post post) throws SQLException {
		postDAO.increaseViewCount(post);
	}

	public List<Post> findPostsByAuthorId(String authorId) throws SQLException {
		return postDAO.findPostsByAuthorId(authorId);
	}
	
	public List<Post> findPostsByKeywordOfTitle(String keyword) throws SQLException {
		return postDAO.findPostsByKeywordOfTitle(keyword);
	}

}