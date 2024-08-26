package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.service.PostManager;

public class DeletePostController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(DeletePostController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String postId = request.getParameter("postId");
		String writerId = request.getParameter("writerId");
		log.debug("Delete Post : {}", postId);

		PostManager manager = PostManager.getInstance();
		HttpSession session = request.getSession();

		if (!UserSessionUtils.hasLogined(session)) {
			return "redirect:/user/login/form";
		}

		if (UserSessionUtils.isLoginUser(writerId, session)) {
			manager.delete(Integer.parseInt(postId));
			return "redirect:/comm/main";
		}

		Post post = manager.findPostByPostId(Integer.parseInt(postId));
		request.setAttribute("post", post);
		request.setAttribute("deleteFailed", true);

		String msg = (UserSessionUtils.isLoginUser("admin", session)) ? "시스템 관리자 게시글은 삭제할 수 없습니다."
				: "타인의 정보는 삭제할 수 없습니다.";
		request.setAttribute("exception", new IllegalStateException(msg));

		return "redirect:/comm/main";
	}
}
