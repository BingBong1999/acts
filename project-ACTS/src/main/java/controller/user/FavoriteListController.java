package controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import exception.UserNotFoundException;
import model.service.FavoriteManager;
import model.service.PostManager;
import model.service.UserManager;
import model.Favorite;
import model.Post;
import model.User;

public class FavoriteListController implements Controller {

	@SuppressWarnings("null")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager manager = UserManager.getInstance();
		FavoriteManager fm = FavoriteManager.getInstance();
		PostManager pm = PostManager.getInstance();
		HttpSession session = request.getSession();
		
		String loginAccountId = UserSessionUtils.getLoginUserId(session);
		List<Favorite> favoriteList = new ArrayList<Favorite>();
		List<Post> postList = new ArrayList<Post>();
		User user = null;

		try {
			
			user = manager.findUserByUserId(loginAccountId);
			favoriteList = fm.findFavoriteListByUserId(Integer.parseInt(user.getId()));
			
		} catch (UserNotFoundException e) {
			return "redirect:/user/myPage";
		}

		for (Favorite data : favoriteList) {
			
			int postId = data.getPostId();
			Post post = pm.findPostByPostId(postId);
			postList.add(post);
		
		}

		request.setAttribute("postList", postList);
		
		return "/user/favoriteList.jsp";
	}
}