package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.service.UserManager;
import model.User;

public class UpdateUserController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager userManager = UserManager.getInstance();
		HttpSession session = request.getSession();
		String accountId = UserSessionUtils.getLoginUserId(session);

		if (request.getMethod().equals("GET")) {

			String updateId = accountId;

			log.debug("UpdateForm Request : {}", updateId);

			User user = userManager.findUserByUserId(updateId);
			request.setAttribute("user", user);

			if (UserSessionUtils.isLoginUser(updateId, session) || UserSessionUtils.isLoginUser("admin", session))
				return "/user/updateForm.jsp";
			
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));
			
			return "/user/view.jsp";
		}

		User updateUser = new User(request.getParameter("id"), request.getParameter("password"), request.getParameter("email"));

		log.debug("Update User : {}", updateUser);

		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);
		
		return "redirect:/user/myInfo";
	}
}