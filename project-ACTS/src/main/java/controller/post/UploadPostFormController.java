package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Post;
import model.service.PostManager;

public class UploadPostFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!UserSessionUtils.hasLogined(request.getSession()))
			return "redirect:/user/login/form";
		
		if ( request.getParameter("postId") != null ) {
			PostManager postManager = PostManager.getInstance();
			Post post = postManager.findPostByPostId(Integer.parseInt(request.getParameter("postId")));
			request.setAttribute("post", post);
		} else {
			request.setAttribute("post", null);
		}
		
		request.setAttribute("loginId", UserSessionUtils.getLoginUserId(request.getSession()));
		
		
		return "/post/postForm.jsp";
	}
}