package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import model.User;
import model.service.UserManager;

public class ListUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager manager = UserManager.getInstance();
		List<User> userList = manager.findUserList();

		request.setAttribute("userList", userList);

		return "/user/usrList.jsp";
	}
}
