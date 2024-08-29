package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Post;

public class PostDAO {

	private static final Logger log = LoggerFactory.getLogger(PostDAO.class);
	private JDBCUtil jdbcUtil;

	public PostDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Post post, List<String> imageUrls) throws Exception {

		try {
			// 생성할 POST의 ID 가져오기
			jdbcUtil.setAutoCommit(false);

			String getIdSql = "SELECT POST_ID_SEQ.nextval FROM dual";
			jdbcUtil.setSqlAndParameters(getIdSql, null);
			ResultSet rs = jdbcUtil.executeQuery();

			int postId = -1;
			
			if (rs.next()) {
				postId = rs.getInt(1);
			} else {
				throw new SQLException("시퀀스에서 postId를 가져올 수 없습니다.");
			}

			// POST 레코드 생성하기
			String postSql = "INSERT INTO POST (ID, TITLE, BODY, CREATED_AT, CATEGORY_ID, VIEW_COUNT, STATUS, PRICE, AUTHOR_ID) "
					+ "VALUES (?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
			Object[] postParams = new Object[] { postId, post.getTitle(), post.getBody(), post.getCategoryId(),
					post.getViewCount(), post.getStatus(), post.getPrice(), post.getAuthorId() };

			jdbcUtil.setSqlAndParameters(postSql, postParams);
			jdbcUtil.executeUpdate();

			// IMAGE 레코드 생성하기
			String imageSql = "INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (?, ?)";

			for (String imageUrl : imageUrls) {
				Object[] imageParams = new Object[] { postId, imageUrl };
				jdbcUtil.setSqlAndParameters(imageSql, imageParams);
				jdbcUtil.executeUpdate();
			}
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
			jdbcUtil.setAutoCommit(true);
		}

		return 0;
	}

	public int update(Post post) throws SQLException {

		String sql = "UPDATE POST " + "SET TITLE=?, BODY=?, CATEGORY_ID=?, PRICE=? " + "WHERE ID=?";
		Object[] param = new Object[] { post.getTitle(), post.getBody(), post.getCategoryId(), post.getPrice() };
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

	public int delete(int id) throws SQLException {

		String sql = "DELETE FROM POST WHERE ID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id });

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

	public Post findPostByPostId(int id) throws SQLException {

		String sql = "SELECT * " + "FROM POST " + "WHERE ID=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			if (rs.next()) {
				Post post = new Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("BODY"),
						rs.getDate("CREATE_AT"), rs.getInt("CATEGORY_ID"), rs.getInt("VIEW_COUNT"),
						rs.getString("STATUS"), rs.getInt("PRICE"), rs.getString("AUTHOR_ID"));

				return post;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

	public List<Post> findAllPosts() throws SQLException {

		String sql = "SELECT * " + "FROM POST " + "ORDER BY ID";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();

			while (rs.next()) {
				Post post = new Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("BODY"),
						rs.getDate("CREATE_AT"), rs.getInt("CATEGORY_ID"), rs.getInt("VIEW_COUNT"),
						rs.getString("STATUS"), rs.getInt("PRICE"), rs.getString("AUTHOR_ID"));

				postList.add(post);
			}

			return postList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

	public List<Post> findPostsByCategory(int category) throws SQLException {

		String sql = "SELECT * " + "FROM POST p JOIN CATEGORY c ON p.CATEGORY_ID=c.CATEGORY_ID "
				+ "WHERE CATEGORY_ID=? " + "ORDER BY p.ID";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category });

		try {

			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();

			while (rs.next()) {
				Post post = new Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("BODY"),
						rs.getDate("CREATE_AT"), rs.getInt("CATEGORY_ID"), rs.getInt("VIEW_COUNT"),
						rs.getString("STATUS"), rs.getInt("PRICE"), rs.getString("AUTHOR_ID"));
				postList.add(post);

				log.debug(rs.getString("postType"));
			}

			return postList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

	public void increaseViewCount(Post post) throws SQLException {

		String sql = "UPDATE POST " + "SET VIEW_COUNT=? " + "WHERE ID=?";
		Object[] param = new Object[] { post.getViewCount() + 1, post.getId() };

		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
	}

	public List<Post> findPostsByAuthorId(String authorId) throws SQLException {

		String sql = "SELECT * " + "FROM POST " + "WHERE AUTHOR_ID=?";
		Object[] param = new Object[] { authorId };

		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();

			while (rs.next()) {
				Post post = new Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("BODY"),
						rs.getDate("CREATE_AT"), rs.getInt("CATEGORY_ID"), rs.getInt("VIEW_COUNT"),
						rs.getString("STATUS"), rs.getInt("PRICE"), rs.getString("AUTHOR_ID"));
				postList.add(post);
			}

			return postList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

	public List<Post> findPostsByKeywordOfTitle(String keyword) throws SQLException {

		String sql = "SELECT * " + "FROM POST " + "WHERE TITLE LIKE '%" + keyword + "%'";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {

			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();

			while (rs.next()) {

				Post post = new Post(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("BODY"),
						rs.getDate("CREATE_AT"), rs.getInt("CATEGORY_ID"), rs.getInt("VIEW_COUNT"),
						rs.getString("STATUS"), rs.getInt("PRICE"), rs.getString("AUTHOR_ID"));

				postList.add(post);
			}

			return postList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

}