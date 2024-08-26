package controller.post;

import java.io.File;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.User;
import model.service.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

public class UploadPostController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(UploadPostController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String curUserId = UserSessionUtils.getLoginUserId(request.getSession());
		UserManager userManager = UserManager.getInstance();
		User user = userManager.findUserByUserId(curUserId);

		File dir = null;
		String title = null;
		String description = null;
		String categoryId = null;
		String status = null;
		String price = null;
		String pType = null;
		String filename = null;
		
		boolean check = ServletFileUpload.isMultipartContent(request);

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
						
						if (item.getFieldName().equals("title"))
							title = value;
						else if (item.getFieldName().equals("description"))
							description = value;
						else if (item.getFieldName().equals("categoryId"))
							categoryId = value;
						else if (item.getFieldName().equals("status"))
							status = value;
						else if (item.getFieldName().equals("price"))
							price = value;
						else if (item.getFieldName().equals("pType"))
							pType = value;
						
					} else {
						
						if (item.getFieldName().equals("image")) {
							
							filename = item.getName();
							
							if (filename == null || filename.trim().length() == 0)
								continue;
							
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

		log.debug("Create title : {}", title);
		
		Post prod = new Post(title, description, filename, Integer.parseInt(categoryId), status,
				Integer.parseInt(price), pType, Integer.parseInt(user.getId()));
		request.setAttribute("post", prod);

		log.debug("Create ProductForm : {}", prod);

		try {

			PostManager postManager = PostManager.getInstance();
			postManager.create(prod);

			log.debug("Create ProductForm : {}", prod);
			
			return "redirect:/comm/main";
		
		} catch (Exception e) {
			
			request.setAttribute("uploadFail", true);
			request.setAttribute("exception", e);
			request.setAttribute("post", prod);
			
			return "/post/postForm.jsp";
		}
	}
}