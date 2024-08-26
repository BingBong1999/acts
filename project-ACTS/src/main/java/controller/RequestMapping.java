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

		mappings.put("/", new ForwardController("index.jsp"));
		mappings.put("/comm/main", new ReadPostListController());
		mappings.put("/comm/search/list", new SearchCategoryController());
		mappings.put("/user/myPage", new MyPageController());
		mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp")); 
		mappings.put("/user/login", new LoginController());
		mappings.put("/user/logout", new LogoutController());
		mappings.put("/user/view", new ViewUserController());
		mappings.put("/comm/buyerPage", new BuyerPostListController());
		mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
		mappings.put("/user/register", new RegisterUserController());
		mappings.put("/user/myInfo", new MyInfoController());
		mappings.put("/user/update", new UpdateUserController());
		mappings.put("/user/myPost", new MyPostController());
		mappings.put("/user/myPage/myBuyerTransaction", new MyBuyerTransactionListController());
		mappings.put("/user/myPage/mySellerTransaction", new MySellerTransactionListController());
		mappings.put("/user/followingList", new FavoriteListController());
		mappings.put("/user/delete", new DeleteUserController());
		mappings.put("/post/upload/form", new UploadPostFormController());
		mappings.put("/post/upload", new UploadPostController());
		mappings.put("/post/update", new UpdatePostController());
		mappings.put("/post/postInfo", new PostInfoController());
		mappings.put("/post/sellerPostUpdate", new SellerUpdatePostController());
		mappings.put("/post/sellerPostInfo", new SellerPostInfoController());
		mappings.put("/post/delete", new DeletePostController());
		mappings.put("/post/search", new PostSearchController());
		mappings.put("/post/transactionForm", new CreateTransactionFormController());
		mappings.put("/post/transaction", new CreateTransactionController());
		mappings.put("/user/transactionInfo", new TransactionInfoController());
		mappings.put("/user/transactionDelete", new DeleteTransactionController());
		mappings.put("/review/upload/form", new UploadReviewFormController());
		mappings.put("/review/upload", new UploadReviewController());
		mappings.put("/chatting/chatting", new ForwardController("/chatting/chatting.jsp")); 
		
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String uri) {
		return mappings.get(uri);
	}
}
