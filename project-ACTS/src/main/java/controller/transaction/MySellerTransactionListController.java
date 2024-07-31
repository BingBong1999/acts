package controller.transaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Transaction;
import model.service.TransactionManager;

public class MySellerTransactionListController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		if (!UserSessionUtils.hasLogined(request.getSession()))
            return "redirect:/user/login/form";
        
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		TransactionManager transactionMan =  TransactionManager.getInstance();
		List<Transaction> transactionList = transactionMan.findMySellerTransactionList(userId);
		
		request.setAttribute("userId", userId);
    	request.setAttribute("transactionList", transactionList);			
		
    	return "/user/mySellerTransaction.jsp";
    }
}