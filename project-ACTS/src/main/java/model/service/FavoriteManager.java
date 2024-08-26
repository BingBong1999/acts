package model.service;

import java.sql.SQLException;

import java.util.List;

import exception.PostNotFoundException;
import model.Favorite;
import model.dao.FavoriteDAO;

public class FavoriteManager {

	private static FavoriteManager favorite = new FavoriteManager();
	private FavoriteDAO favoriteDAO;

	private FavoriteManager() {
		try {
			favoriteDAO = new FavoriteDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FavoriteManager getInstance() {
		return favorite;
	}

	public int create(Favorite favorite) throws SQLException {
		return favoriteDAO.create(favorite);
	}

	public int removeByPostIdAndUserId(int postId, int userId) throws SQLException, PostNotFoundException {
		return favoriteDAO.removeByPostIdAndUserId(postId, userId);
	}

	public Favorite findFavoriteByPostIdAndUserId(int postId, int userId) throws SQLException, PostNotFoundException {
		Favorite favorite = favoriteDAO.findFavoriteByPostIdAndUserId(postId, userId);

		if (favorite == null) {
			return null;
		}
		
		return favorite;
	}

	public List<Favorite> findFavoriteListByUserId(int userId) throws SQLException {
		return favoriteDAO.findFavoriteListByUserId(userId);
	}
}