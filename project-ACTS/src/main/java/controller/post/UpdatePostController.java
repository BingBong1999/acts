package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.UserManager;
import model.service.PostManager;
import model.Post;
import model.User;

public class UpdatePostController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(UpdatePostController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String updateId = request.getParameter("accountId");
    		int postId = Integer.parseInt(request.getParameter("postId"));

    		log.debug("PostUpdateForm Request : {}{}", updateId, postId);
    		
    		UserManager manager = UserManager.getInstance(); 
    		User user = manager.findUser(updateId);	
    		
 
			
			request.setAttribute("user", user);			

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session)) {
				 
				// ���� �α����� ����ڰ� ���� ��� ������� ��� -> ���� ����
				return "/post/postUpdateForm.jsp";   // �˻��� �Խñ� ������ post update form���� ����     
			
			}    
			
			// else ���� �Ұ����� ��� ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("postUpdateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("Ÿ���� �Խñ��� ������ �� �����ϴ�."));      
		
			return "/post/postInfo.jsp";
		}
		PostManager postManager = PostManager.getInstance();
//		Post post = postManager.findPost(postId); // �����Ϸ��� �Խñ� ���� �˻�
		
		// POST request
		Post updatePost = new Post(
				Integer.parseInt(request.getParameter("postId")),
				request.getParameter("title"),
				request.getParameter("description"),
				request.getParameter("imgUrl"),
				Integer.parseInt(request.getParameter("categoryId")),
				request.getParameter("status"),
				Integer.parseInt(request.getParameter("price")),
				request.getParameter("pType")
				);
			
		log.debug("Update Post : {}", updatePost);
		postManager.update(updatePost);
        return "redirect:/post/postInfo";

	
	}
}

