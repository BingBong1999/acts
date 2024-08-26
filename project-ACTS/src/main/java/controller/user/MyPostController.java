package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

import model.Post;
import model.service.PostManager;
import model.service.UserManager;

public class MyPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager userManager = UserManager.getInstance();
		PostManager postManager = PostManager.getInstance();
		HttpSession session = request.getSession();

		String userId1 = UserSessionUtils.getLoginUserId(session);
		int userId = Integer.parseInt(userManager.findUserByUserId(userId1).getId());

		List<Post> postList = postManager.findMyPostList(userId);

		request.setAttribute("postList", postList);
		
		return "/user/myPost.jsp";
	}
}