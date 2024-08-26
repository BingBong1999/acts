package controller.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.User;
import model.service.PostManager;
import model.service.UserManager;

public class CreateTransactionFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
		UserManager userManager = UserManager.getInstance();
		String postUserNickName = null;
		PostManager postManager = PostManager.getInstance();
		Post post = null;
		User user = null;
		int postId;

		if (!UserSessionUtils.hasLogined(request.getSession()))
			return "redirect:/user/login/form";

		postId = Integer.parseInt(request.getParameter("postId"));
		user = userManager.findUserByUserId(curUserId);
		post = postManager.findPost(postId);
		postUserNickName = postManager.getPostUserNickName(Integer.parseInt(request.getParameter("writerId")));

		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.setAttribute("nickname", postUserNickName);

		int iwriterId = Integer.parseInt(user.getId());
		
		if (iwriterId == post.getWriterId()) {

			request.setAttribute("trasactionCreateFailed", true);
			request.setAttribute("exception", new IllegalStateException("본인의 글은 주문할 수 없습니다."));

			return "/post/sellerPostInfo.jsp";
		}

		return "/post/transactionForm.jsp";
	}
}