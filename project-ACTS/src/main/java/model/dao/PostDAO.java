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
	private JDBCUtil jdbcUtil = null;

	public PostDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Post post) throws SQLException {

		String sql = "INSERT INTO POST VALUES (post_id_seq.nextval, ?, ?, ?, DEFAULT, DEFAULT, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { post.getTitle(), post.getDesc(), post.getImgUrl(), post.getStatus(),
				post.getPrice(), post.getpType(), post.getWriterId(), post.getCategoryId() };
		
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

	public int update(Post post) throws SQLException {
		
		String sql = "UPDATE POST " + "SET title=?, description=?, imageUrl=?, categoryId=?, status=?, price=? "
				+ "WHERE postId=?";
		Object[] param = new Object[] { post.getTitle(), post.getDesc(), post.getImgUrl(), post.getCategoryId(),
				post.getStatus(), post.getPrice(), post.getPostId() };
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

	public int remove(int postId) throws SQLException {
		
		String sql = "DELETE FROM POST WHERE postId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

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

	public Post findPost(int postId) throws SQLException {
		
		String sql = "SELECT title, description, imageUrl, createdTime, categoryId, "
				+ "views, status, price, postType, writerId " + "FROM POST " + "WHERE postId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				
				Post post = new Post(
						postId, rs.getString("title"), rs.getString("description"), rs.getString("imageUrl"),
						rs.getDate("createdTime"), rs.getInt("categoryId"), rs.getInt("views"), rs.getString("status"),
						rs.getInt("price"), rs.getString("postType"), rs.getInt("writerId"));
				
				return post;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public List<Post> findPostList() throws SQLException {
		
		String sql = "SELECT postId, title, imageUrl, views, status, price, postType, writerId " + "FROM POST "
				+ "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();
			
			while (rs.next()) {
				
				Post post = new Post(rs.getInt("postId"), rs.getString("title"), rs.getString("imageUrl"), rs.getInt("views"),
						rs.getString("status"), rs.getInt("price"), rs.getString("postType"), rs.getInt("writerId"));
				
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

	public List<Post> findPostListUseCategory(String cName) throws SQLException {
		
		String sql = "SELECT postId, title, imageUrl, views, status, price, postType, writerId "
				+ "FROM POST p JOIN CATEGORY c ON p.categoryId=c.categoryId " + "WHERE categoryName=? "
				+ "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { cName });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();
			
			while (rs.next()) {
				Post post = new Post(rs.getInt("postId"), rs.getString("title"), rs.getString("imageUrl"), rs.getInt("views"),
						rs.getString("status"), rs.getInt("price"), rs.getString("postType"), rs.getInt("writerId"));
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

	public List<Post> SearchPostList(String title) throws SQLException {
		
		String sql = "SELECT * " + "FROM POST " + "WHERE title LIKE '%" + title + "%'";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();

			while (rs.next()) {
				
				Post post = new Post(rs.getInt("postId"), rs.getString("title"), rs.getString("imageUrl"),
						rs.getInt("views"), rs.getString("status"), rs.getInt("price"), rs.getString("postType"),
						rs.getInt("writerId"));

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

	public void increasePostView(Post post) throws SQLException {
		
		String sql = "UPDATE POST " + "SET views=? " + "WHERE postId=?";
		Object[] param = new Object[] { post.getViews() + 1, post.getPostId() };

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

	public String findUserNickNameByUserId(int userId) throws SQLException {
		
		String sql = "SELECT nickName " + "FROM ACCOUNT " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) 
				return rs.getString("nickName");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public List<Post> findBuyerPostList() throws SQLException {
		
		String sql = "SELECT * " + "FROM POST " + "WHERE POSTTYPE='b' " + "ORDER BY postId";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();
			
			while (rs.next()) {
				
				Post post = new Post(rs.getInt("postId"), rs.getString("title"), rs.getString("imageUrl"), rs.getInt("views"),
						rs.getString("status"), rs.getInt("price"), rs.getString("postType"), rs.getInt("writerId"));
				
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

	public List<Post> findMyPostList(int userId) throws SQLException {
		
		String sql = "SELECT * " + "FROM POST " + "WHERE writerId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Post> postList = new ArrayList<Post>();
			
			while (rs.next()) {
				
				Post post = new Post(rs.getInt("postId"), rs.getString("title"), rs.getString("description"),
						rs.getString("imageUrl"), rs.getDate("createdTime"), rs.getInt("categoryId"),
						rs.getInt("views"), rs.getString("status"), rs.getInt("price"), rs.getString("postType"),
						rs.getInt("writerId"));
				
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