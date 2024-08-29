package controller.post;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.service.*;

public class UploadPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		if (ServletFileUpload.isMultipartContent(request)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {

				PostManager postManager = PostManager.getInstance();

				List<FileItem> formItems = upload.parseRequest(request);

				String title = null;
				String body = null;
				int categoryId = -1;
				int price = -1;
				List<String> imageUrls = new ArrayList<String>();

				for (FileItem item : formItems) {
					if (item.isFormField()) {
						switch (item.getFieldName()) {
						case "title":
							title = item.getString("UTF-8");
							break;
						case "body":
							body = item.getString("UTF-8");
							break;
						case "categoryId":
							categoryId = Integer.parseInt(item.getString("UTF-8"));
							break;
						case "price":
							price = Integer.parseInt(item.getString("UTF-8"));
							break;
						}
					} else if (item.getFieldName().equals("image")) {
						ServletContext context = request.getServletContext();
						String filePath = context.getRealPath("/") + "imageResource/" + item.getName();
						
						System.out.println(filePath);
						File storeFile = new File(filePath);
						item.write(storeFile);
						imageUrls.add(item.getName()); // 저장된 이미지의 경로를 리스트에 추가
					}
				}

				Post post = new Post(-1, title, body, new Date(0), categoryId, 0, "available", price,
						UserSessionUtils.getLoginUserId(request.getSession()));

				postManager.create(post, imageUrls);

				return "redirect:/comm/main";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("uploadFail", true);
				request.setAttribute("exception", e);
			}

		}

		return "/post/postForm.jsp";

	}

}