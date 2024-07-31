package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
	
	public static final String USER_SESSION_KEY = "accountId";

	public static String getLoginUserId(HttpSession session) {
		
		String accountId = (String) session.getAttribute(USER_SESSION_KEY);

		return accountId;
	}

	public static boolean hasLogined(HttpSession session) {
		
		if (getLoginUserId(session) != null)
			return true;
		
		return false;
	}

	public static boolean isLoginUser(String accountId, HttpSession session) {
		
		String loginUser = getLoginUserId(session);
		
		if (loginUser == null)
			return false;
		
		return loginUser.equals(accountId);
	}
}
