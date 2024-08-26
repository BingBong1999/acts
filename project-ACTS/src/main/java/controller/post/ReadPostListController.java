package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import model.Post;
import model.service.PostManager;

public class ReadPostListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PostManager manager = PostManager.getInstance();
		request.setAttribute("postList", null);
		
		List<Post> postList = manager.findAllPosts();
		request.setAttribute("postList", postList);

		return "/comm/main.jsp";
	}
}
