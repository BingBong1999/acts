package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import model.UserAction.UserActionRecord;
import model.Post;
import model.UserAction.UserActionProcessed;

import java.util.ArrayList;

public class UserActionDAO {

	private JDBCUtil jdbcUtil;
//	private static final int BATCH_SIZE = 10;
	private static final int BATCH_SIZE = 2;
	
	public UserActionDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(UserActionRecord record) throws Exception {

		try {

			String sql = "INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION) "
					+ "VALUES (USER_ACTION_ID_SEQ.nextval, ?, ?, ?, SYSDATE, ?)";
			Object[] params = new Object[] { record.getUserId(), record.getPostId(), record.getActionType(), record.getDuration() };

			jdbcUtil.setSqlAndParameters(sql, params);

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
	
	public int delete(Post post) throws SQLException {

		String sql = "DELETE FROM USER_ACTION WHERE POST_ID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { post.getId() });

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

	public List<UserActionProcessed> preprocessUserActions() {

		List<UserActionProcessed> processeds = new ArrayList<>();

		String sql = "SELECT USER_ID, POST_ID, COUNT(*) AS action_count, "
				+ "SUM(CASE WHEN ACTION_TYPE = 'liked' THEN 1 ELSE 0 END) as liked, "
				+ "SUM(CASE WHEN ACTION_TYPE = 'view_duration' THEN DURATION ELSE 0 END) as view_duration, "
				+ "SUM(CASE WHEN ACTION_TYPE = 'view_count' THEN 1 ELSE 0 END) as view_count " 
				+ "FROM USER_ACTION "
				+ "GROUP BY USER_ID, POST_ID";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {});

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {

				int actionCount = rs.getInt("action_count");

				if (actionCount >= BATCH_SIZE) {
					
					String userId = rs.getString("USER_ID");
					int postId = rs.getInt("POST_ID");
					int liked = rs.getInt("liked");
					double viewDuration = rs.getDouble("view_duration");
					int viewCount = rs.getInt("view_count");

					UserActionProcessed processed = new UserActionProcessed(userId, postId, liked, viewDuration, viewCount);
					processeds.add(processed);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return processeds;
	}
}
