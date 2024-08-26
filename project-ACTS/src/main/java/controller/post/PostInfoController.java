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
import model.User;

public class PostInfoController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(PostInfoController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PostManager postManager = PostManager.getInstance();
		ReviewManager reviewManager = ReviewManager.getInstance();

		HttpSession session = request.getSession();
		FavoriteManager fm = FavoriteManager.getInstance();
		Post post = null;
		User user = null;
		List<Review> reviewList = null;

		int fOrNot;
		int likeRequest = Integer.parseInt(request.getParameter("likeRequest"));
		int postId = -1;

		String userId = UserSessionUtils.getLoginUserId(session);

		String postUserNickName = null;

		try {

			post = postManager.findPostByPostId(Integer.parseInt(request.getParameter("postId")));
			postId = post.getId();
			log.debug("PostInfo Request : {}", post.getId());
			postUserNickName = post.getAuthorId();
			postManager.increaseViewCount(post);
			reviewList = reviewManager.findReviewList(post.getId());

		} catch (PostNotFoundException e) {
			return "redirect:/post/postList";
		}

		if (fm.findFavoriteByPostIdAndUserId(postId, Integer.parseInt(userId)) != null) {
			fOrNot = 1;
		} else {
			fOrNot = 0;
		}

		if (likeRequest == 1) {
			fm.create(new Favorite(postId, Integer.parseInt(userId)));
			fOrNot = 1;
		} else if (likeRequest == 0) {
			fm.removeByPostIdAndUserId(postId, Integer.parseInt(userId));
			fOrNot = 0;
		} else {
			likeRequest = -1;
		}

		request.setAttribute("fOrNot", fOrNot);
		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.setAttribute("nickname", postUserNickName);
		request.setAttribute("reviewList", reviewList);

		return "/post/postInfoTest.jsp";
	}
}