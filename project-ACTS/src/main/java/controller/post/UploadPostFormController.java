package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;

public class UploadPostFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!UserSessionUtils.hasLogined(request.getSession()))
			return "redirect:/user/login/form";

		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
		
		return "/post/postFormTest.jsp";
	}
}