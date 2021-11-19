package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.User;

public class MyPageController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
		UserManager manager = UserManager.getInstance();
		String accountId = request.getParameter("accountId");

    	User user = null;
    	try {
			user = manager.findUser(accountId);	// ����� ���� �˻�
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/login";
		}	
		
    	request.setAttribute("user", user);		// ����� ���� ����				
		return "/user/myPage.jsp";				// ����� ���� ȭ������ �̵�
    }

}
