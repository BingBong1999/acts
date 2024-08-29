package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.ImageDAO;

public class ImageManager {

	private static ImageManager imageManager = new ImageManager();
	private ImageDAO imageDAO;

	private ImageManager() {
		imageDAO = new ImageDAO();
	}

	public static ImageManager getInstance() {
		return imageManager;
	}

	public int create(int postId, String imageUrl) throws SQLException {
		return imageDAO.create(postId, imageUrl);
	}

	public List<String> findImageUrlsByPostId(int postId) {
		return imageDAO.findImageUrlsByPostId(postId);
	}

}
