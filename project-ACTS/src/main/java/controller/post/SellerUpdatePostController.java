package controller.post;

import java.io.File;
import java.util.Date;
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

public class SellerUpdatePostController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(UpdatePostController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		UserManager userManager = UserManager.getInstance();
		PostManager postManager = PostManager.getInstance();
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		
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
		String filename = null;
		String writerId = null;
		
		boolean check = ServletFileUpload.isMultipartContent(request);

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";
		}

		if (request.getMethod().equals("GET")) {
			int iPostId = Integer.parseInt(request.getParameter("postId"));
			log.debug("PostUpdateForm Request : {}, {}", userId, iPostId);

			user = userManager.findUserByUserId(userId);
			post = postManager.findPostByPostId(iPostId);

			request.setAttribute("user", user);
			request.setAttribute("post", post);

			if (userId == post.getAuthorId()) {
				return "/post/postUpdateForm.jsp";
			}

			postUserNickName = post.getAuthorId();
			request.setAttribute("nickname", postUserNickName);
			request.setAttribute("postUpdateFailed", true);
			request.setAttribute("exception", new IllegalStateException("타인의 게시글은 수정할 수 없습니다."));

			return "/post/sellerPostInfo.jsp";
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
						else if (item.getFieldName().equals("writerId"))
							writerId = value;

					} else {

						if (item.getFieldName().equals("image")) {
							
							filename = item.getName();
							
							if (filename == null || filename.trim().length() == 0) {
								
								int iPostId = Integer.parseInt(postId);
								
								
								Post post2 = postManager.findPostByPostId(iPostId);
								filename = post2.getImageUrl();
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
		
		
		Post updatePost = new Post(-1, title, description, filename, new Date(0), 
				Integer.parseInt(categoryId), Integer.parseInt(views), status, Integer.parseInt(price), writerId);
		
		request.setAttribute("post", updatePost);
		postUserNickName = writerId;
		log.debug("Update Post : {}", updatePost);
		
		postManager.increaseViewCount(updatePost);
		postManager.update(updatePost);

		request.setAttribute("postId", updatePost.getId());
		request.setAttribute("post", updatePost);
		request.setAttribute("nickname", postUserNickName);
		
		return "/post/sellerPostInfo.jsp";
	}
}
