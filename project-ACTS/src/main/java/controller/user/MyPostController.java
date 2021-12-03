package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.User;
import model.dao.UserDAO;

public class MyPostController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserDAO userDao = new UserDAO();
		List<Post> postList;
		
    	
    	postList = userDao.findMyPostList(userId);	// �α��� ����� ���� �˻�	
		
    	
    	request.setAttribute("postList", postList);		// ����� ���� ����				
		return "/user/myPost.jsp";				// ����� ���� ȭ������ �̵�
    }

}
