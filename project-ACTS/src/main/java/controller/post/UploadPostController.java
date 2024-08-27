package controller.post;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.service.*;

public class UploadPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		PostManager postManager = PostManager.getInstance();
		HttpSession session = request.getSession();

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int viewCount = 0;
		String status = "available";
		int price = Integer.parseInt(request.getParameter("price"));
		String authorId = UserSessionUtils.getLoginUserId(session);

		Post post = new Post(-1, title, body, new Date(0), categoryId, viewCount, status, price, authorId);

		List<String> imageUrls = extractImageUrls(body);

		try {
			postManager.create(post, imageUrls);
			return "redirect:/comm/main";

		} catch (Exception e) {
			request.setAttribute("uploadFail", true);
			request.setAttribute("exception", e);
			return "/post/postForm.jsp";
		}

	}

	private List<String> extractImageUrls(String body) {

		List<String> imageUrls = new ArrayList<>();
		String imgTagPattern = "<img[^>]+src=\"([^\"]+)\"[^>]*>";

		Pattern pattern = Pattern.compile(imgTagPattern);
		Matcher matcher = pattern.matcher(body);

		while (matcher.find()) {
			imageUrls.add(matcher.group(1));
		}
		return imageUrls;
	}
}