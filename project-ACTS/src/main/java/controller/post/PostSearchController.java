package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import model.Post;
import model.dao.PostDAO;

public class PostSearchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		PostDAO postdao = new PostDAO();
		String title = request.getParameter("searchKeyword");
		List<Post> postList = postdao.SearchPostList(title);

		request.setAttribute("postList", postList);

		return "/post/searchPostList.jsp";
	}

}
