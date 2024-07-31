package controller.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.Transaction;
import model.User;
import model.service.PostManager;
import model.service.TransactionManager;
import model.service.UserManager;

public class CreateTransactionController implements Controller {
	
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
		String transTitle;
		String transContents;

		postId = Integer.parseInt(request.getParameter("postId"));
		user = userManager.findUser(curUserId);
		post = postManager.findPost(postId);
		postUserNickName = postManager.getPostUserNickName(Integer.parseInt(request.getParameter("writerId")));

		TransactionManager transactionManager = TransactionManager.getInstance();
		transTitle = request.getParameter("transTitle");
		transContents = request.getParameter("transContents");
		Transaction transaction = new Transaction(user, post, transTitle, transContents);
		transactionManager.create(transaction);

		request.setAttribute("user", user);
		request.setAttribute("post", post);
		request.setAttribute("nickname", postUserNickName);
		request.setAttribute("trasactionCreateSuccess", true);
		
		return "/post/sellerPostInfo.jsp";
	}
}
