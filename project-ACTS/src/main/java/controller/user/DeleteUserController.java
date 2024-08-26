package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.User;
import model.service.UserManager;

public class DeleteUserController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String deleteId = request.getParameter("accountId");
		log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();

		if ((UserSessionUtils.isLoginUser("admin", session) && !deleteId.equals("admin"))
				|| (!UserSessionUtils.isLoginUser("admin", session) && UserSessionUtils.isLoginUser(deleteId, session))) {
			
			manager.delete(deleteId);
			
			if (UserSessionUtils.isLoginUser("admin", session))
				return "redirect:/comm/main";
			else
				return "redirect:/user/logout";
		}

		User user = manager.findUserByUserId(deleteId);
		
		request.setAttribute("user", user);
		request.setAttribute("deleteFailed", true);
		
		String msg = (UserSessionUtils.isLoginUser("admin", session)) ? "시스템 관리자 정보는 삭제할 수 없습니다."
				: "타인의 정보는 삭제할 수 없습니다.";
		
		request.setAttribute("exception", new IllegalStateException(msg));
		
		return "/comm/main.jsp";
	}
}
