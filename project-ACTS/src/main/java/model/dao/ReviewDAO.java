package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class ReviewDAO {

	private JDBCUtil jdbcUtil = null;

	public ReviewDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Review review) throws SQLException {
		
		String sql = "INSERT INTO POSTREVIEW VALUES (review_id_seq.nextval, DEFAULT, ?, ?, ?, ?)";
		Object[] param = new Object[] { review.getContent(), review.getScore(), review.getReviewerId(),
				review.getPostId() };

		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return 0;
	}

	public int update(Review review) throws SQLException {
		
		String sql = "UPDATE POSTREVIEW " + "SET content=?, score=? " + "WHERE reviewId=?";
		Object[] param = new Object[] { review.getContent(), review.getScore() };
		
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return 0;
	}

	public int remove(int reviewId) throws SQLException {
		
		String sql = "DELETE FROM POSTREVIEW WHERE reviewId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { reviewId });

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return 0;
	}

	public List<Review> findReviewList(int postId) throws SQLException {
		
		String sql = "SELECT reviewId, createdTime, reviewContent, score, reviewerId, postId " + "FROM POSTREVIEW "
				+ "WHERE postId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Review> reviewList = new ArrayList<Review>();
			
			while (rs.next()) {
				
				Review review = new Review(rs.getInt("reviewId"), rs.getDate("createdTime"),
						rs.getString("reviewContent"), rs.getInt("score"), rs.getInt("reviewerId"),
						rs.getInt("postId"));
				
				reviewList.add(review);
			}
			
			return reviewList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
}