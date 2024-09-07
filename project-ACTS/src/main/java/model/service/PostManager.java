package model.service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

import java.sql.SQLException;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
		
		post.setCategoryName(Category.getNameById(post.getCategoryId()));
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

	public JSONArray convertAllPostToJsonArray() throws SQLException, UserNotFoundException {

		List<Post> posts = findAllPosts();

		JSONArray rslt = new JSONArray();

		for (Post p : posts) {
			rslt.put(new JSONObject().put("id", p.getId())
									.put("title", p.getTitle())
									.put("keywords", extractKeywordsFromBody(p.getBody()))
									.put("category", p.getCategoryName()));
		}

		return rslt;
	}

	public JSONObject convertPostToJsonArray(Post post) throws SQLException, UserNotFoundException {

		JSONObject rslt = new JSONObject();

		rslt.put("id", post.getId())
			.put("title", post.getTitle())
			.put("keywords", new JSONArray(extractKeywordsFromBody(post.getBody())))
			.put("category", post.getCategoryName());

		return rslt;
	}

	public JSONArray extractKeywordsFromBody(String body) {

		body = body.replaceAll("<br>", "");
		
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		KomoranResult analyzeResultList = komoran.analyze(body);
		List<String> nouns = analyzeResultList.getNouns();
		
		JSONArray rslt = new JSONArray();
		
		for(String noun : nouns) {
			rslt.put(noun);
		}

		return rslt;
	}

}