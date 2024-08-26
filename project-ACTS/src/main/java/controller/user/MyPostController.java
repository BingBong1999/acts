package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

import model.Post;
import model.service.PostManager;

public class MyPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PostManager postManager = PostManager.getInstance();
		HttpSession session = request.getSession();


		List<Post> postList = postManager.findPostsByAuthorId(UserSessionUtils.getLoginUserId(session));

		request.setAttribute("postList", postList);
		
		return "/user/myPost.jsp";
	}
}