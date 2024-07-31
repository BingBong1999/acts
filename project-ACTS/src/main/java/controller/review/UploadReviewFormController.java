package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.service.PostManager;

public class UploadReviewFormController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(UploadReviewFormController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		if (!UserSessionUtils.hasLogined(request.getSession()))
			return "redirect:/user/login/form";

		String reviewPostId = request.getParameter("postId");
		log.debug("ReviewPostId : {}", reviewPostId);

		PostManager manager = PostManager.getInstance();
		Post post = manager.findPost(Integer.parseInt(reviewPostId)); // find post

		request.setAttribute("post", post);
		
		return "/review/reviewForm.jsp";
	}

}
