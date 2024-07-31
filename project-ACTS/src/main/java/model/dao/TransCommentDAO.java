package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.TransComment;

public class TransCommentDAO {

	private JDBCUtil jdbcUtil = null;

	public TransCommentDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(TransComment comment) throws SQLException {
		
		String sql = "INSERT INTO TRANSCOMMENT VALUES (?, ?, comment_id_seq.nextval, DEFAULT, ?)";
		Object[] param = new Object[] { comment.getTransId(), comment.getCommenterId(), comment.getCommentContent() };

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

	public List<TransComment> findCommentListByTransId(int transId) throws SQLException {
		
		String sql = "SELECT commentId, transId, commenterId, createdTime, commentContent " + "FROM TRANSCOMMENT "
				+ "WHERE transId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { transId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<TransComment> commentList = new ArrayList<TransComment>();
			
			while (rs.next()) {
				
				TransComment comment = new TransComment(rs.getInt("commentId"), rs.getInt("transId"),
						rs.getInt("commenterId"), rs.getDate("createdTime"), rs.getString("commentContent"));
				
				commentList.add(comment);
			}
			
			return commentList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public TransComment findComment(int commentId) throws SQLException {
		
		String sql = "SELECT commentId, transId, commenterId, createdTime, commentContent " + "FROM TRANSCOMMENT "
				+ "WHERE commentId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { commentId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				TransComment commentO = new TransComment(rs.getInt("commentId"), rs.getInt("transId"),
						rs.getInt("commenterId"), rs.getDate("createdTime"), rs.getString("commentContent"));

				return commentO;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
}