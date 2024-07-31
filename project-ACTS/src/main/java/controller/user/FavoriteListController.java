package controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

import model.service.FavoriteManager;
import model.service.PostManager;
import model.service.UserManager;
import model.service.UserNotFoundException;
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
			
			user = manager.findUser(loginAccountId);
			favoriteList = fm.findFavoriteListByUserId(user.getUserId());
			
		} catch (UserNotFoundException e) {
			return "redirect:/user/myPage";
		}

		for (Favorite data : favoriteList) {
			
			int postId = data.getPostId();
			Post post = pm.findPost(postId);
			postList.add(post);
		
		}

		request.setAttribute("postList", postList);
		
		return "/user/favoriteList.jsp";
	}
}