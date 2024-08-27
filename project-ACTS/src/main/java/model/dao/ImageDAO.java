package model.dao;

import java.sql.SQLException;

public class ImageDAO {

	private JDBCUtil jdbcUtil = null;

	public ImageDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(int postId, String imageUrl) throws SQLException {

		String sql = "INSERT INTO images (POST_ID, IMAGE_URL) VALUES (?, ?)";
		Object[] param = new Object[] { postId, imageUrl };

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

}
