package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

import java.util.List;
import java.util.ArrayList;

public class UserDAO {

	private JDBCUtil jdbcUtil = null;

	public UserDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(User user) throws SQLException {

		String sql = "INSERT INTO ACCOUNT (ID, PASSWORD, EMAIL) VALUES (?, ?, ?)";
		Object[] param = new Object[] { user.getId(), user.getPassword(), user.getEmail() };
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

	public int update(User user) throws SQLException {

		String sql = "UPDATE ACCOUNT " + "SET PASSWORD=?, EMAIL=? " + "WHERE ID=?";
		Object[] param = new Object[] { user.getPassword(), user.getEmail(), user.getId() };

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

	public int delete(String id) throws SQLException {

		String sql = "DELETE FROM ACCOUNT WHERE ID=?";
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

	public User findUserByUserId(String id) throws SQLException {

		String sql = "SELECT * " + "FROM ACCOUNT " + "WHERE ID=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			if (rs.next()) {
				User user = new User(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("EMAIL"));
				return user;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}
	
	public List<String> findAllUserId() throws SQLException {

		List<String> userIds = new ArrayList<>();
		
		String sql = "SELECT ID " + "FROM ACCOUNT";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				userIds.add(rs.getString("ID"));
			}
			
			return userIds;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

}