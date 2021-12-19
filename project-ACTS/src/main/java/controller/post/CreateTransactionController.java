package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Post;
import model.Transaction;
import model.User;
import model.service.PostManager;
import model.service.TransactionManager;
import model.service.UserManager;

public class CreateTransactionController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateTransactionController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
		UserManager userManager = UserManager.getInstance();
		User user = userManager.findUser(curUserId);
		
		PostManager postManager = PostManager.getInstance();
		Post post = null;
		
		//���߿� ����: ���� �� ���̸� �ֹ� ���ϵ���
		
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
    	if (request.getMethod().equals("GET")) {	
    		int postId = Integer.parseInt(request.getParameter("postId"));
    		log.debug("PostUpdateForm Request : {}", postId);
    		
    		post = postManager.findPost(postId);
    		
    	}
    	
    	TransactionManager transactionManager = TransactionManager.getInstance();
    	
    	Transaction transaction = new Transaction(user, post);
    	transactionManager.create(transaction);
    	
		return "redirect:/comm/main";
	}

}
