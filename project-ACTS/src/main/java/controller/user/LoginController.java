package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

import model.service.UserManager;

public class LoginController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String accountId = request.getParameter("accountId");
		String password = request.getParameter("password");

		try {

			UserManager manager = UserManager.getInstance();
			manager.login(accountId, password);

			HttpSession session = request.getSession();
			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, accountId);

			return "redirect:/comm/main";
		
		} catch (Exception e) {
			
			request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			
			return "/user/loginForm.jsp";
		}
	}
}
