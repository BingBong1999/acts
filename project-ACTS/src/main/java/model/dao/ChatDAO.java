package model.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import model.Message;

public class ChatDAO {

	private JDBCUtil jdbcUtil;

	public ChatDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int createMessage(Message message) throws Exception {

		try {

			String messageSql = "INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) "
					+ "VALUES (MESSAGE_ID_SEQ.nextval, ?, ?, ?, SYSDATE)";
			Object[] messageParams = new Object[] { message.getSenderId(), message.getReceiverId(), message.getContent()};

			jdbcUtil.setSqlAndParameters(messageSql, messageParams);

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

	public List<Message> findLatestMessagesPerReceiverBySenderId(String senderId) throws Exception {

		String query = "SELECT * FROM MESSAGE WHERE (RECEIVER_ID, CREATED_AT) "
				+ "IN (SELECT RECEIVER_ID, MAX(CREATED_AT) FROM MESSAGE WHERE SENDER_ID=? GROUP BY RECEIVER_ID)";
		
		jdbcUtil.setSqlAndParameters(query, new Object[] { senderId });

		try {
			List<Message> messages = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Message message = new Message(rs.getInt("ID"), rs.getString("SENDER_ID"),
						rs.getString("RECEIVER_ID"), rs.getString("CONTENT"), rs.getDate("CREATED_AT").toString());

				messages.add(message);
			}

			return messages;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;

	}
	
	public List<Message> findMessagesBySenderIdAndReceiverId(String senderId, String receiverId) throws Exception {
		String query = "SELECT * FROM MESSAGE WHERE (SENDER_ID=? AND RECEIVER_ID=?) OR (SENDER_ID=? AND RECEIVER_ID=?) ORDER BY CREATED_AT ASC";
		
		jdbcUtil.setSqlAndParameters(query, new Object[] { senderId, receiverId, receiverId, senderId });

		try {
			List<Message> messages = new ArrayList<>();
			ResultSet rs = jdbcUtil.executeQuery();

			while (rs.next()) {
				Message message = new Message(rs.getInt("ID"), rs.getString("SENDER_ID"),
						rs.getString("RECEIVER_ID"), rs.getString("CONTENT"), rs.getDate("CREATED_AT").toString());

				messages.add(message);
			}

			return messages;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

}
