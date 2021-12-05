package controller.post;

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
		//����� ���� �ѱ�
    	UserManager manager = UserManager.getInstance();
    	PostManager postManager = PostManager.getInstance();
    	HttpSession session = request.getSession();
    	String loginAccountId = UserSessionUtils.getLoginUserId(session);
    	User user = null;
    	Post post = null;
    	
    	String postUserNickName = null;
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
  
    	
		if (request.getMethod().equals("GET")) {	
			//���� �� �ۼ����� writerId�� ��� ����
//			String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
//		    User curUser = manager.findUser(curUserId);
//		    System.out.println("curUser" + curUser);
//		    int writerId = curUser.getUserId();
		    
		    
		    
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
//    		String updateId = request.getParameter("accountId");
			
    		int postId = Integer.parseInt(request.getParameter("postId"));
    		log.debug("PostUpdateForm Request : {}, {}", loginAccountId, postId);
    		
    		user = manager.findUser(loginAccountId);
    		int writerId = user.getUserId();
    		
    		post = postManager.findPost(postId);
    		
    		
			request.setAttribute("user", user);	
			request.setAttribute("post", post);
		
//			request.setAttribute("writerId", post.getWriterId());
			log.debug("Updateform request Post : {}", post);
			if (writerId == post.getWriterId()) {
				 
				// ���� �α����� ����ڰ� ���� ��� ������� ��� -> ���� ����
				return "/post/postUpdateForm.jsp";   // �˻��� �Խñ� ������ post update form���� ����     
			
			}    
			
			// else ���� �Ұ����� ��� ����� ���� ȭ������ ���� �޼����� ����
			postUserNickName = postManager.getPostUserNickName( post.getWriterId());
			System.out.println("��������: " + postUserNickName);
			request.setAttribute("nickname", postUserNickName);
			request.setAttribute("postUpdateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("Ÿ���� �Խñ��� ������ �� �����ϴ�."));      
		
			return "/post/postInfo.jsp";
		}
	
		
		// POST request
		System.out.print("update post id: " + request.getParameter("postId"));
		Post updatePost = new Post(
				Integer.parseInt(request.getParameter("postId")),
				request.getParameter("title"),
				request.getParameter("description"),
				request.getParameter("imgUrl"),
				Integer.parseInt(request.getParameter("categoryId")),
				Integer.parseInt(request.getParameter("views")),
				request.getParameter("status"),
				Integer.parseInt(request.getParameter("price")),
				request.getParameter("pType"),
				Integer.parseInt(request.getParameter("writerId")));
			
		postUserNickName = postManager.getPostUserNickName(Integer.parseInt(request.getParameter("writerId")));
		log.debug("Update Post : {}", updatePost);
		postManager.increasePostView(updatePost);
		postManager.update(updatePost);
		
		request.setAttribute("postId", updatePost.getPostId());	
		request.setAttribute("post", updatePost);	
		request.setAttribute("nickname", postUserNickName);
        return "/post/postInfo.jsp";

	
	}
}

