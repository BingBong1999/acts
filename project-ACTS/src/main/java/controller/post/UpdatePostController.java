package controller.post;

import java.io.File;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.service.UserManager;
import model.service.PostManager;
import model.Post;
import model.User;

public class UpdatePostController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(UpdatePostController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		UserManager manager = UserManager.getInstance();
		PostManager postManager = PostManager.getInstance();
		
		HttpSession session = request.getSession();
		String loginAccountId = UserSessionUtils.getLoginUserId(session);
		
		User user = null;
		Post post = null;
		String postUserNickName = null;
		File dir = null;
		String postId = null;
		String title = null;
		String description = null;
		String categoryId = null;
		String views = null;
		String status = null;
		String price = null;
		String pType = null;
		String filename = null;
		String writerId = null;
		
		boolean check = ServletFileUpload.isMultipartContent(request);

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";
		}

		if (request.getMethod().equals("GET")) {
			
			int iPostId = Integer.parseInt(request.getParameter("postId"));
			log.debug("PostUpdateForm Request : {}, {}", loginAccountId, iPostId);

			user = manager.findUser(loginAccountId);
			int iwriterId = user.getUserId();

			post = postManager.findPost(iPostId);

			request.setAttribute("user", user);
			request.setAttribute("post", post);

			if (iwriterId == post.getWriterId()) {
				return "/post/postUpdateForm.jsp";
			}

			postUserNickName = postManager.getPostUserNickName(post.getWriterId());
			
			request.setAttribute("nickname", postUserNickName);
			request.setAttribute("postUpdateFailed", true);
			request.setAttribute("exception", new IllegalStateException("타인의 게시글은 수정할 수 없습니다."));

			return "/post/postInfo.jsp";
		}

		if (check) {

			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			dir = new File(path);

			if (!dir.exists())
				dir.mkdir();

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(10 * 1024);
				factory.setRepository(dir);

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10 * 1024 * 1024);
				upload.setHeaderEncoding("utf-8");

				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);

				for (int i = 0; i < items.size(); ++i) {
					FileItem item = (FileItem) items.get(i);

					String value = item.getString("utf-8");

					if (item.isFormField()) {
						
						if (item.getFieldName().equals("postId"))
							postId = value;
						else if (item.getFieldName().equals("title"))
							title = value;
						else if (item.getFieldName().equals("description"))
							description = value;
						else if (item.getFieldName().equals("categoryId"))
							categoryId = value;
						else if (item.getFieldName().equals("views"))
							views = value;
						else if (item.getFieldName().equals("status"))
							status = value;
						else if (item.getFieldName().equals("price"))
							price = value;
						else if (item.getFieldName().equals("pType"))
							pType = value;
						else if (item.getFieldName().equals("writerId"))
							writerId = value;

					} else {

						if (item.getFieldName().equals("image")) {
							
							filename = item.getName();
							
							if (filename == null || filename.trim().length() == 0) {								
								int iPostId = Integer.parseInt(postId);
								filename = postManager.getImgUrl(iPostId);
								continue;
							}
							
							filename = filename.substring(filename.lastIndexOf("\\") + 1);
							File file = new File(dir, filename);
							item.write(file);
						}
					}
				}

			} catch (SizeLimitExceededException e) {
				e.printStackTrace();
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Post updatePost = new Post(Integer.parseInt(postId), title, description, filename,
				Integer.parseInt(categoryId), status, Integer.parseInt(price), pType, Integer.parseInt(writerId));
		request.setAttribute("post", updatePost);

		postUserNickName = postManager.getPostUserNickName(Integer.parseInt(writerId));
		log.debug("Update Post : {}", updatePost);
		postManager.increasePostView(updatePost);
		postManager.update(updatePost);

		request.setAttribute("postId", updatePost.getPostId());
		request.setAttribute("post", updatePost);
		request.setAttribute("nickname", postUserNickName);

		return "/post/postInfo.jsp";
	}
}
