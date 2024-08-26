package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Category;
import model.Post;
import model.service.PostManager;

public class SearchCategoryController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(SearchCategoryController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String categoryName = request.getParameter("categoryName");
		log.debug("SearchCategoryController Request : {}", categoryName);

		PostManager manager = PostManager.getInstance();
		List<Post> postList = manager.findPostsByCategory(Category.getIdByName(categoryName));

		request.setAttribute("postList", postList);
		
		return "/post/searchPostList.jsp";
	}
}
