package controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.post.*;
import controller.review.UploadReviewController;
import controller.review.UploadReviewFormController;
import controller.transaction.*;

public class RequestMapping {
	
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {

		// 일반
		mappings.put("/", new ForwardController("index.jsp"));
		
		
		// 게시글
		mappings.put("/comm/main", new ReadPostListController());
		mappings.put("/comm/search/list", new SearchCategoryController());
		mappings.put("/post/form", new UploadPostFormController());
		mappings.put("/post/upload", new UploadPostController());
		mappings.put("/post/update", new UpdatePostController());
		mappings.put("/post/postInfo", new PostInfoController());
		mappings.put("/post/delete", new DeletePostController());
		mappings.put("/post/search", new PostSearchController());
		
		// 사용자
		mappings.put("/user/myPage", new MyPageController());
		mappings.put("/user/login/form", new ForwardController("/user/login.jsp")); 
		mappings.put("/user/login", new LoginController());
		mappings.put("/user/logout", new LogoutController());
		mappings.put("/user/view", new ViewUserController());
		mappings.put("/user/register/form", new ForwardController("/user/join.jsp"));
		mappings.put("/user/register", new RegisterUserController());
		mappings.put("/user/myInfo", new MyInfoController());
		mappings.put("/user/update", new UpdateUserController());
		mappings.put("/user/myPost", new MyPostController());
		mappings.put("/user/followingList", new FavoriteListController());
		mappings.put("/user/delete", new DeleteUserController());
		
		// 거래
		mappings.put("/user/myPage/myBuyerTransaction", new ReadTransactionListController());
		mappings.put("/post/transactionForm", new CreateTransactionFormController());
		mappings.put("/post/transaction", new CreateTransactionController());
		mappings.put("/user/transactionInfo", new TransactionInfoController());
		mappings.put("/user/transactionDelete", new DeleteTransactionController());
		
		// 후기
		mappings.put("/review/upload/form", new UploadReviewFormController());
		mappings.put("/review/upload", new UploadReviewController());
		
		// 채팅
		mappings.put("/chat/list", new ForwardController("/chat/list.jsp")); 
		
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String uri) {
		return mappings.get(uri);
	}
}
