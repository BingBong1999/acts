package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.PostManager;
import model.service.PostNotFoundException;
import model.Post;

public class PostInfoController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	
		PostManager manager = PostManager.getInstance();
		HttpSession session = request.getSession();

    	Post post = null;
    	String postUserNickName = null;
    	try {
    		post = manager.findPost(Integer.parseInt(request.getParameter("postId")));	// ����� ���� �˻�
    		postUserNickName = manager.getPostUserNickName(Integer.parseInt(request.getParameter("writerId")));
    		manager.increasePostView(post);
    	} catch (PostNotFoundException e) {				
	        return "redirect:/post/postList";
		}	
		
    	request.setAttribute("post", post);		// ����� ���� ����
    	request.setAttribute("nickname", postUserNickName);
		return "/post/postInfo.jsp";				// ����� ���� ȭ������ �̵�
    }

}
