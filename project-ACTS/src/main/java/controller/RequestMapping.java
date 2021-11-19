package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
    	
    	//�ʱ�ȭ�� �� �α��ΰ� �α׾ƿ�
        mappings.put("/", new ForwardController("index.jsp")); //���: �ʱ�ȭ���� index.jsp�� �̵��ϰ�
        //�ʱ�-����ȭ��
        mappings.put("/comm/main", new ForwardController("/comm/main.jsp"));
        mappings.put("/comm/search", new ForwardController("/comm/search.jsp"));
        mappings.put("/user/myPage", new MyPageController());
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp")); //���: �α��� ��ư�� ������ /user/loginForm.jsp�� �̵��ϰ�
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/view", new ViewUserController());
        
        
        mappings.put("/user/list", new ListUserController()); //���: ��������Ʈ ���°� ����ڰ� �ƴ� administrator�� ���°Ŷ� ��
        
        
        // ȸ�� ���� �� ��û�� ���� ��û ó�� ���� (���� Ŀ�´�Ƽ ���� �޴� �߰��� ����)
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        
        //���������� ����
        mappings.put("/user/myInfo", new MyInfoController()); //���: �������� ���� ��Ʈ�ѷ�
        //�ڽ��� ������ ������Ʈ�ϰ� ����ϴ°�.
        mappings.put("/user/update/form", new ViewUserController()); //���: �ڱ� �ڽ��� ������ ������ �� ��  
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/myPost", new ForwardController("/user/myPost.jsp"));
        mappings.put("/user/participatingTransaction", new ForwardController("/user/participatingTransaction.jsp"));
        mappings.put("/user/followingList", new ForwardController("/user/followingList.jsp"));
        
        mappings.put("/user/delete", new DeleteUserController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
