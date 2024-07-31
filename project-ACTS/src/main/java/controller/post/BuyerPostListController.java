package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import model.Post;
import model.dao.PostDAO;

public class BuyerPostListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PostDAO postdao = new PostDAO();
		request.setAttribute("postList", null);
		
		List<Post> postList = postdao.findBuyerPostList();
		request.setAttribute("postList", postList);

		return "/post/buyerPostList.jsp";
	}
}
