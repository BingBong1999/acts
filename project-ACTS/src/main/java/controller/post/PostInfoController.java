package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import exception.PostNotFoundException;

import model.service.FavoriteManager;
import model.service.PostManager;
import model.service.ReviewManager;
import model.Favorite;
import model.Post;
import model.Review;

public class PostInfoController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(PostInfoController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PostManager postManager = PostManager.getInstance();
		ReviewManager reviewManager = ReviewManager.getInstance();
		FavoriteManager fm = FavoriteManager.getInstance();

		HttpSession session = request.getSession();

		Post post = null;
		List<Review> reviewList = null;

		boolean isLiked = false;
		int likeRequest = Integer.parseInt(request.getParameter("likeRequest"));
		int postId = -1;

		String loginAccountId = UserSessionUtils.getLoginUserId(session);

		try {

			post = postManager.findPostByPostId(Integer.parseInt(request.getParameter("postId")));
			postId = post.getId();
			log.debug("PostInfo Request : {}", post.getId());

			postManager.increaseViewCount(post);
			reviewList = reviewManager.findReviewList(post.getId());

		} catch (PostNotFoundException e) {
			return "redirect:/post/postList";
		}

		if (UserSessionUtils.hasLogined(session)) {

			if (likeRequest == 1) {
				fm.create(new Favorite(postId, loginAccountId));
				isLiked = true;
			} else if (likeRequest == 0) {
				fm.removeByPostIdAndUserId(postId, loginAccountId);
				isLiked = false;
			} else {
				isLiked = fm.findFavoriteByPostIdAndUserId(postId, loginAccountId) != null;
			}

		} else {
			isLiked = false;
		}

		request.setAttribute("isLiked", isLiked);
		request.setAttribute("loginId", loginAccountId);
		request.setAttribute("post", post);
		request.setAttribute("reviewList", reviewList);

		return "/post/postInfo.jsp";
	}
}