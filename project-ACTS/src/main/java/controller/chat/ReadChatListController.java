package controller.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Message;
import model.service.ChatManager;

import controller.Controller;
import controller.user.UserSessionUtils;

public class ReadChatListController implements Controller {
	
	ChatManager chatManager = ChatManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            return handleGet(request, response); 
        } 
        
//        else if ("POST".equalsIgnoreCase(method)) {
//            return handlePost(request, response);
//        }

        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "지원되지 않는 요청 방식입니다.");
        return null;
    }

    private String handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String action = request.getParameter("action");

        try {
        	
            if ("list".equals(action)) {
            	String loginId = UserSessionUtils.getLoginUserId(request.getSession());
            	List<Message> messageList = chatManager.findLatestMessagesPerReceiverBySenderId(loginId);
            	
            	response.setContentType("application/json");
            	response.setCharacterEncoding("UTF-8");
            	String json = new Gson().toJson(messageList);
        		PrintWriter out = response.getWriter();
        		out.write(json);
        		out.flush();
                
            } else if ("history".equals(action)) {
//                int chatRoomId = Integer.parseInt(request.getParameter("chatRoomId"));
//                List<Message> messages = chatManager.findMessagesByChatRoomId(chatRoomId);
//                request.setAttribute("messages", messages);
//                return "/chat/list.jsp"; // DispatcherServlet에서 forward 처리
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");
        }

        return null;
    }

    // POST 요청 처리 메서드
//    private String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        
//        HttpSession session = request.getSession();
//        String loginId = UserSessionUtils.getLoginUserId(session);
//
//        try {
//            if ("sendMessage".equals(action)) {
//                int chatRoomId = Integer.parseInt(request.getParameter("chatRoomId"));
//                String senderId = request.getParameter("senderId");
//                String receiverId = request.getParameter("receiverId");
//                String content = request.getParameter("content");
//
//                Message message = new Message(-1, chatRoomId, senderId, receiverId, content, new Date());
//                chatManager.createMessage(message);
//                return "redirect:/chat?action=history&chatRoomId=" + chatRoomId; // Redirect 처리
//            } else if ("createRoom".equals(action)) {
//                String roomName = request.getParameter("roomName");
//                chatManager.createChatRoom(roomName, loginId);
//                return "redirect:/chat?action=list"; // Redirect 처리
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");
//        }
//
//        return null;
//    }
}
