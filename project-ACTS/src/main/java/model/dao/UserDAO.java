package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	
	private JDBCUtil jdbcUtil = null;

	public UserDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(User user) throws SQLException {
		
		String sql = "INSERT INTO ACCOUNT VALUES (user_id_seq.nextval, ?, ?, ?, ?, ?, DEFAULT, ?, ?)";
		Object[] param = new Object[] { user.getPhone(), user.getEmail(), user.getName(), user.getRegNum(),
				user.getPassword(), user.getAccountId(), user.getNickName() };
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
		
		String sql = "UPDATE ACCOUNT " + "SET phoneNumber=?, emailAddress=?, userName=?, " + "password=?, nickName=? " + "WHERE accountId=?";
		Object[] param = new Object[] { user.getPhone(), user.getEmail(), user.getName(), user.getPassword(),
				user.getNickName(), user.getAccountId() };
		
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

	public int remove(String accountId) throws SQLException {
		
		String sql = "DELETE FROM ACCOUNT WHERE accountId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { accountId });

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

	public User findUser(String accountId) throws SQLException {
		
		String sql = "SELECT userId, phoneNumber, emailAddress, userName, registrationNumber, password, accountId, joinDate, nickName "
				+ "FROM ACCOUNT " + "WHERE accountId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { accountId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				User user = new User(rs.getInt("userId"), accountId, rs.getString("password"), rs.getString("userName"),
						rs.getString("emailAddress"), rs.getString("phoneNumber"), rs.getString("registrationNumber"),
						rs.getDate("joinDate"), rs.getString("nickName"));
				
				return user;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public User findUserByPrimaryKey(int userId) throws SQLException {
		
		String sql = "SELECT phoneNumber, emailAddress, userName, registrationNumber, password, joinDate, accountId, nickName "
				+ "FROM ACCOUNT " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				User user = new User(userId, rs.getString("accountId"), rs.getString("password"), rs.getString("userName"),
						rs.getString("emailAddress"), rs.getString("phoneNumber"), rs.getString("registrationNumber"),
						rs.getDate("joinDate"), rs.getString("nickName"));
				
				return user;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public List<User> findUserList() throws SQLException {
		
		String sql = "SELECT phoneNumber, emailAddress, userName, registrationNumber, joinDate, accountId, nickName "
				+ "FROM ACCOUNT " + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<User> userList = new ArrayList<User>();
			
			while (rs.next()) {
				
				User user = new User(rs.getString("accountId"), null, rs.getString("userName"), rs.getString("emailAddress"),
						rs.getString("phoneNumber"), rs.getString("registrationNumber"),
						valueOf(rs.getDate("joinDate")), rs.getString("nickName"));
				
				userList.add(user);
			}
			
			return userList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	private java.util.Date valueOf(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existingUser(String accountId) throws SQLException {
		
		String sql = "SELECT count(*) FROM ACCOUNT WHERE accountId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { accountId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return false;
	}

	public String findAccountIdByUserId(String userId) throws SQLException {
		
		String sql = "SELECT accountId " + "FROM ACCOUNT " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next())
				return rs.getString("accountId");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		
		return null;
	}
}