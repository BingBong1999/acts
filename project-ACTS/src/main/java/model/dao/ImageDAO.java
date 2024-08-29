package model.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
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

	public List<String> findImageUrlsByPostId(int postId) {

		String sql = "SELECT * " + "FROM IMAGE " + "WHERE POST_ID=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			List<String> imageUrls = new ArrayList<>();
			
			while (rs.next()) {
				String imageUrl = rs.getString("IMAGE_URL");
				imageUrls.add(imageUrl);
			}

			return imageUrls;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

}
