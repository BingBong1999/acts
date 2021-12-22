package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;
import model.TransComment;

public class TransCommentDAO {
	
private JDBCUtil jdbcUtil = null;
	
	public TransCommentDAO() {			
		jdbcUtil = new JDBCUtil();
	}
	
	public int create(TransComment comment) throws SQLException {
		String sql = "INSERT INTO TRANSCOMMENT VALUES (comment_id_seq.nextval, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { comment.getTransId(), comment.getCommenterId(), comment.getCreateTime(), comment.getCommentContent()};	
		System.out.println("sql: " + sql);
		System.out.println("param: " + param);
		for (Object p : param) {
			System.out.println(p);
		}

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
        String sql = "SELECT commentId, transId, commentorId, createTime, commentContent " 
        		   + "FROM TRANSCOMMENT "
        		   + "WHERE transId=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {transId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<TransComment> commentList = new ArrayList<TransComment>();
			while (rs.next()) {
				TransComment comment = new TransComment(
						rs.getInt("commentId"),
						rs.getInt("transId"),
						rs.getInt("commentorId"),
						rs.getDate("createTime"),
						rs.getString("commentContent"));
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

}