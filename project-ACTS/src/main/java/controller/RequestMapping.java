package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.post.*;
import controller.review.UploadReviewController;
import controller.review.UploadReviewFormController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	
    	//초기화면 및 로그인과 로그아웃
        mappings.put("/", new ForwardController("index.jsp")); //경원: 초기화면은 index.jsp로 이동하게
        //초기-메인화면
        mappings.put("/comm/main", new ListPostController());
        mappings.put("/comm/search", new ForwardController("/comm/search.jsp"));
        mappings.put("/comm/search/searchForm", new ForwardController("/comm/searchForm.jsp"));
        mappings.put("/comm/search/list", new SearchCategoryController());
        mappings.put("/user/myPage", new MyPageController());
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp")); //경원: 로그인 버튼을 누르면 /user/loginForm.jsp로 이동하게
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/view", new ViewUserController());
        mappings.put("/comm/buyerPage", new BuyerPostListController());       
        
        mappings.put("/user/list", new ListUserController()); //경원: 유저리스트 보는건 사용자가 아닌 administrator가 보는거라서 뺌
      
        
        
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        
        //마이페이지 관리
        mappings.put("/user/myInfo", new MyInfoController()); //경원: 내정보로 가는 컨트롤러
        //자신의 정보를 업데이트하고 등록하는것.
//        mappings.put("/user/update/form", new ForwardController("/user/updateForm.jsp")); //경원: 자기 자신의 정보를 수정하고자 할 때  
        
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/myPost", new MyPostController());

        
  
        mappings.put("/user/myPage/myBuyerTransaction", new MyBuyerTransactionListController());
        mappings.put("/user/myPage/mySellerTransaction", new MySellerTransactionListController());

      

    
        mappings.put("/user/followingList", new FavoriteListController());


        
        mappings.put("/user/delete", new DeleteUserController());
        
        // 게시글 관리
        mappings.put("/post/upload/form", new UploadPostFormController()); //게시글 작성 폼 요청(로그인 아이디 정보 넘김->writerId로 쓰기위함)
        mappings.put("/post/upload", new UploadPostController());
        mappings.put("/post/update", new UpdatePostController());
        mappings.put("/post/postInfo", new PostInfoController());
        mappings.put("/post/sellerPostUpdate", new SellerUpdatePostController());
        mappings.put("/post/sellerPostInfo", new SellerPostInfoController());
        mappings.put("/post/delete", new DeletePostController());
        mappings.put("/post/search", new PostSearchController());

        mappings.put("/post/transaction", new CreateTransactionController());
        logger.info("Initialized Request Mapping!");
        
        // 댓글 관리
        mappings.put("/review/upload/form", new UploadReviewFormController());
        mappings.put("/review/upload", new UploadReviewController());  
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
