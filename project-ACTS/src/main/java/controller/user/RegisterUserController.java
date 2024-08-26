package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import exception.ExistingUserException;
import model.User;
import model.service.UserManager;

public class RegisterUserController implements Controller {
 
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
       	User user = new User(
			request.getParameter("id"),
			request.getParameter("password"),
			request.getParameter("email"));
		
        log.debug("Create User : {}", user);

		try {
			
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			
	        return "redirect:/comm/main";
	        
		} catch (ExistingUserException e) {
			
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			
			return "/user/registerForm.jsp";
		}
    }
}
