package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.service.FavoriteManager;
import model.service.PostManager;
import model.service.PostNotFoundException;
import model.service.ReviewManager;
import model.service.UserManager;
import model.Favorite;
import model.Post;
import model.Review;
import model.User;

public class PostInfoController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(PostInfoController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager manager = UserManager.getInstance();
		PostManager postManager = PostManager.getInstance();
		ReviewManager reviewManager = ReviewManager.getInstance();

		HttpSession session = request.getSession();
		FavoriteManager fm = FavoriteManager.getInstance();
		Post post = null;
		User user = null;
		List<Review> reviewList = null;

		int fOrNot;
		int setting = Integer.parseInt(request.getParameter("setting"));
		int postId = -1;
		int userId = -1;

		String loginAccountId = UserSessionUtils.getLoginUserId(session);

		if (loginAccountId != null) {
			user = manager.findUser(loginAccountId);
			userId = user.getUserId();
		}

		String postUserNickName = null;

		try {

			post = postManager.findPost(Integer.parseInt(request.getParameter("postId")));
			postId = post.getPostId();
			log.debug("PostInfo Request : {}", post.getPostId());
			postUserNickName = postManager.getPostUserNickName(post.getWriterId());
			postManager.increasePostView(post);
			reviewList = reviewManager.findReviewList(post.getPostId());

		} catch (PostNotFoundException e) {
			return "redirect:/post/postList";
		}

		if (fm.findFavoriteByPostIdAndUserId(postId, userId) != null) {
			fOrNot = 1;
		} else {
			fOrNot = 0;
		}

		if (setting == 1) {
			fm.create(new Favorite(postId, userId));
			fOrNot = 1;
		} else if (setting == 0) {
			fm.removeByPostIdAndUserId(postId, userId);
			fOrNot = 0;
		} else {
			setting = -1;
		}

		request.setAttribute("fOrNot", fOrNot);
		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.setAttribute("nickname", postUserNickName);
		request.setAttribute("reviewList", reviewList);

		return "/post/postInfo.jsp";
	}
}