package model.service;

import java.sql.SQLException;

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

}
