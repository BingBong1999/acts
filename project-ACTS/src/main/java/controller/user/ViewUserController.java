package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import exception.UserNotFoundException;
import model.service.UserManager;
import model.User;

public class ViewUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager manager = UserManager.getInstance();
		String userId = request.getParameter("userId");

		User user = null;
		
		try {
			user = manager.findUser(userId);
		} catch (UserNotFoundException e) {
			return "redirect:/user/view";
		}

		request.setAttribute("user", user);
		
		return "/user/myInfo.jsp";
	}
}
