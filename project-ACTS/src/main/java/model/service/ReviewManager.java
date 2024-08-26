package model.service;

import java.sql.SQLException;

import java.util.List;

import model.Review;
import model.User;
import model.dao.*;

public class ReviewManager {

	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;
	private UserDAO userDAO;

	private ReviewManager() {
		
		try {
			reviewDAO = new ReviewDAO();
			userDAO = new UserDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ReviewManager getInstance() {
		return reviewMan;
	}

	public int create(Review review) throws SQLException {
		return reviewDAO.create(review);
	}

	public int update(Review review) throws SQLException {
		return reviewDAO.update(review);
	}

	public int remove(int reviewId) throws SQLException {
		return reviewDAO.remove(reviewId);
	}

	public List<Review> findReviewList(int postId) throws SQLException {
		
		List<Review> reviewList = reviewDAO.findReviewList(postId);

		for (Review review : reviewList) {
			User user = userDAO.findUserByUserId(Integer.toString(review.getReviewerId()));
			review.setUser(user);
		}

		return reviewList;
	}
}