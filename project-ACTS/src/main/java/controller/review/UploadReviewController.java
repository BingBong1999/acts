package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.Review;
import model.User;
import model.service.PostManager;
import model.service.ReviewManager;
import model.service.UserManager;

public class UploadReviewController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(UploadReviewController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
		UserManager userManager = UserManager.getInstance();
		User user = userManager.findUser(curUserId);

		PostManager postManager = PostManager.getInstance();
		int postId = Integer.parseInt(request.getParameter("postId"));
		Post post = postManager.findPost(postId);

		Review review = new Review(request.getParameter("content"), Integer.parseInt(request.getParameter("score")),
				user.getUserId(), post.getPostId());

		log.debug("Create Review : {}", review);

		ReviewManager manager = ReviewManager.getInstance();
		manager.create(review);

		return "redirect:/comm/main"; // Move to post info
	}
}
