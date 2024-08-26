package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionDAO {
	
	private JDBCUtil jdbcUtil = null;

	public TransactionDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Transaction transaction) throws SQLException {

		String sql = "INSERT INTO TRANSACTION VALUES (trans_id_seq.nextval, ?, ?, DEFAULT, ?, ?)";
		Object[] param = new Object[] { transaction.getUser().getId(), transaction.getPost().getPostId(),
				transaction.getTransTitle(), transaction.getTransContents() };
		
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

	public Transaction findTransaction(int transId) throws SQLException {
		
		String sql = "SELECT transTitle, transContents " + "FROM TRANSACTION " + "WHERE transId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { transId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				
				Transaction transaction = new Transaction(transId, rs.getString("transTitle"), rs.getString("transContents"));
				
				return transaction;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public int remove(int transId) throws SQLException {
		
		String sql = "DELETE TRANSACTION WHERE transId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { transId });

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

	public List<Transaction> findMyTransactionList(int userId) throws SQLException {
		
		String sql = "SELECT * " + "FROM TRANSACTION " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Transaction> transactionList = new ArrayList<Transaction>();
			
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getInt("transId"), rs.getDate("transDate"), userId,
						rs.getInt("postId"), rs.getString("transTitle"), rs.getNString("transContents"));
				transactionList.add(transaction);
			}
			
			return transactionList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public List<Transaction> findMySellerTransactionList(int userId) throws SQLException {
		
		String sql = "SELECT t.transId, t.transDate, t.userId, t.postId, t.transTitle, t.transContents "
				+ "FROM TRANSACTION t, POST p " + "WHERE t.postId = p.postId AND p.writerId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			
			ResultSet rs = jdbcUtil.executeQuery();
			List<Transaction> transactionList = new ArrayList<Transaction>();
			
			while (rs.next()) {
				
				Transaction transaction = new Transaction(rs.getInt("transId"), rs.getDate("transDate"),
						rs.getInt("userId"), rs.getInt("postId"), rs.getString("transTitle"),
						rs.getNString("transContents"));
				
				transactionList.add(transaction);
			}
			
			return transactionList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
}