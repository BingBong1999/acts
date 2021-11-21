package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.PostManager;
import model.service.PostNotFoundException;
import model.service.UserManager;
import model.Post;
import model.User;

public class PostInfoController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(PostInfoController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		UserManager manager = UserManager.getInstance();
		PostManager postManager = PostManager.getInstance();
		HttpSession session = request.getSession();
    	Post post = null;
    	
//    	String loginAccountId = UserSessionUtils.getLoginUserId(session);
//    	User user = manager.findUser(loginAccountId);	
    	
    	String postUserNickName = null;
    	try {
    		
    		post = postManager.findPost(Integer.parseInt(request.getParameter("postId")));	// ����� ���� �˻�
    		System.out.println(post);
    		log.debug("PostInfo Request : {}", post.getPostId());
    		postUserNickName = postManager.getPostUserNickName(Integer.parseInt(request.getParameter("writerId")));
    		
    		System.out.println("�г���" + postUserNickName);
    		postManager.increasePostView(post);
    	} catch (PostNotFoundException e) {				
	        return "redirect:/post/postList";
		}	
		
    	
//    	request.setAttribute("user", user);	
    	request.setAttribute("post", post);		// ����� ���� ����
    	request.setAttribute("nickname", postUserNickName);
		return "/post/postInfo.jsp";				// ����� ���� ȭ������ �̵�
    }

}
