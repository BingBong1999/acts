package controller.transaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Transaction;
import model.service.TransactionManager;
import model.service.UserManager;

public class ReadTransactionListController implements Controller {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager manager = UserManager.getInstance();
		HttpSession session = request.getSession();
		TransactionManager transactionMan = TransactionManager.getInstance();

		String userId1 = UserSessionUtils.getLoginUserId(session);
		int userId = Integer.parseInt(manager.findUserByUserId(userId1).getId());
		List<Transaction> transactionList;

		transactionList = transactionMan.findMyTransactionList(userId);

		request.setAttribute("userId", userId);
		request.setAttribute("transactionList", transactionList);
		
		return "/user/myBuyerTransaction.jsp";
	}
}
