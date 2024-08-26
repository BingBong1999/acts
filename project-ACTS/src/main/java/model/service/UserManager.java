package model.service;

import java.sql.SQLException;

import exception.ExistingUserException;
import exception.PasswordMismatchException;
import exception.UserNotFoundException;

import model.User;
import model.dao.UserDAO;

public class UserManager {

	private static UserManager userManager = new UserManager();
	private UserDAO userDAO;

	private UserManager() {
		userDAO = new UserDAO();
	}

	public static UserManager getInstance() {
		return userManager;
	}

	public int create(User user) throws SQLException, ExistingUserException, UserNotFoundException {

		if (findUserByUserId(user.getId()) != null)
			throw new ExistingUserException(user.getId() + "는 존재하는 아이디입니다.");

		return userDAO.create(user);
	}

	public int update(User user) throws SQLException, UserNotFoundException {
		return userDAO.update(user);
	}

	public int delete(String id) throws SQLException, UserNotFoundException {
		return userDAO.delete(id);
	}

	public User findUserByUserId(String id) throws SQLException, UserNotFoundException {

		User user = userDAO.findUserByUserId(id);

		if (user == null)
			throw new UserNotFoundException(id + "는 존재하지 않는 아이디입니다.");

		return user;
	}

	public boolean login(String id, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {

		if (!isMatchPassword(id, password))
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");

		return true;
	}

	public boolean isMatchPassword(String id, String password) throws SQLException {

		User user = userDAO.findUserByUserId(id);

		if (password == null) {
			return false;
		}

		return user.getPassword().equals(password);
	}

}