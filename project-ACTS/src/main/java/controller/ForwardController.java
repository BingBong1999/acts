package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
	
	private String forwardUrl;

	public ForwardController(String forwardUrl) {
		if (forwardUrl == null) {
			throw new NullPointerException("forwardUrl is null. �̵��� URL�� �Է��ϼ���.");
		}
		this.forwardUrl = forwardUrl;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return forwardUrl;
	}
}
