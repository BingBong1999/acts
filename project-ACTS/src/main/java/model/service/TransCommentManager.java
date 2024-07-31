package model.service;

import java.sql.SQLException;

import java.util.List;

import model.TransComment;
import model.dao.*;

public class TransCommentManager {

	private static TransCommentManager cm = new TransCommentManager();
	private TransCommentDAO tcDAO;

	private TransCommentManager() {
		try {
			tcDAO = new TransCommentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static TransCommentManager getInstance() {
		return cm;
	}

	public int create(TransComment transComment) throws SQLException {
		return tcDAO.create(transComment);
	}

	public List<TransComment> findCommentListByTransId(int transId) throws SQLException {
		List<TransComment> commentList = tcDAO.findCommentListByTransId(transId);

		return commentList;
	}

	public TransComment findComment(int commentId) throws SQLException {
		return tcDAO.findComment(commentId);
	}
}