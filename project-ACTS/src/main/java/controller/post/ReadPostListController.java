package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Post;
import model.service.PostManager;

public class ReadPostListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PostManager manager = PostManager.getInstance();
		
		List<Post> postList = manager.findAllPosts();
		request.setAttribute("postList", postList);
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		List<Post> recommendedPostList = manager.findRecommendPersonalizedPostsByUserId(userId);
		request.setAttribute("recommendedPostList", recommendedPostList);
		
		request.setAttribute("userId", userId);

		return "/comm/main.jsp";
	}
}
