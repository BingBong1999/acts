package controller.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.service.TransactionManager;

public class DeleteTransactionController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(DeleteTransactionController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String transId = request.getParameter("transId");

		log.debug("Delete Transaction : {}", transId);

		TransactionManager transManager = TransactionManager.getInstance();
		HttpSession session = request.getSession();

		if (!UserSessionUtils.hasLogined(session))
			return "redirect:/user/login/form";
		

		transManager.remove((Integer.parseInt(transId)));

		return "redirect:/comm/main";
	}
}
