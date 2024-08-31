<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ACTS - 다양한 주니어들과 소비자들의 아이디어와 열정이 모이는 곳</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Noto Sans KR', sans-serif;
        }

        .chat-container {
            max-width: 900px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            height: 650px; 
        }

        .user-list {
            max-height: 600px; 
            overflow-y: auto;
            border-right: 2px solid #dee2e6;
            padding-right: 10px;
            
        }

        .user-item {
            padding: 20px; 
            border-bottom: 1px solid #dee2e6;
            cursor: pointer;
            min-height: 70px; 
        }

        .user-item:hover {
            background-color: #e9ecef;
        }

        .user-id {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .recent-message {
		    font-size: 0.9rem;
		    color: #6c757d;
		    overflow: hidden; 
		    white-space: nowrap;
		    text-overflow: ellipsis;
		    max-width: 100%; 
        }

        .chat-box {
            max-height: 600px; /* 높이 조정 */
            overflow-y: auto;
            padding: 10px;
            margin-bottom: 350px;
        }

		.chat-input-box {
		    padding: 10px 0;
		    margin-top: auto;
		    display: flex; 
		}
        .message-item {
            margin-bottom: 15px;
        }

        .message-content {
            padding: 10px;
            border-radius: 5px;
            background-color: #f1f1f1;
            display: inline-block;
            max-width: 80%;
        }

        .message-time {
            font-size: 0.8rem;
            color: #6c757d;
            margin-left: 5px;
        }
        .text-muted {
        	margin-top: 200px;
		    justify-content: center;
		    align-items: center;
		    height: 100%; 
		    text-align: center;
        }
        .chat-input-box {
		    padding: 10px 0;
		}
		
		.chat-input-box input {
		    flex: 1;
		    border-radius: 4px;
		}
		
		.chat-input-box button {
		    white-space: nowrap;
		}
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp"%>
<h2 class="text-center font-weight-bold" style="margin-top: 30px; ">내 채팅</h2>
<div class="chat-container">
    <div class="row">
        <div class="col-md-4 user-list" id="userList">
            <!-- 사용자 목록이 여기에 표시됩니다. -->
        </div>
        <div class="col-md-8">
            <div class="chat-box" id="chatBox">
                <p class="text-muted">사용자를 선택하여 채팅 내역을 확인하세요.</p>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // 샘플 데이터
    const sampleUsers = [
        { 
            id: "User1", 
            recentMessage: "안녕하세요! 어떻게 지내세요?", 
            messages: [
                { content: "안녕하세요!", time: "10:00 AM" },
                { content: "어떻게 지내세요?", time: "10:05 AM" },
                { content: "요즘 바쁘죠?", time: "10:10 AM" }
            ]
        },
        { 
            id: "User2", 
            recentMessage: "오늘 회의는 몇 시에 시작하나요?", 
            messages: [
                { content: "회의는 2시에 시작해요.", time: "09:30 AM" },
                { content: "몇 시에 오실 건가요?", time: "09:35 AM" },
                { content: "늦지 않게 도착할게요.", time: "09:40 AM" }
            ]
        },
        { 
            id: "User3", 
            recentMessage: "프로젝트 파일을 업로드했습니다.", 
            messages: [
                { content: "프로젝트 파일을 업로드했습니다.", time: "08:45 AM" },
                { content: "확인 부탁드립니다.", time: "08:50 AM" },
                { content: "감사합니다!", time: "08:55 AM" }
            ]
        },
        { 
            id: "User4", 
            recentMessage: "점심 뭐 먹을까요?", 
            messages: [
                { content: "점심 뭐 먹을까요?", time: "12:00 PM" },
                { content: "한식 어때요?", time: "12:05 PM" },
                { content: "좋아요, 가시죠!", time: "12:10 PM" }
            ]
        },
        { 
            id: "User5", 
            recentMessage: "오전에 보낸 이메일 확인 부탁드립니다.", 
            messages: [
                { content: "오전에 보낸 이메일 확인 부탁드립니다.", time: "07:30 AM" },
                { content: "확인했어요. 감사합니다.", time: "07:35 AM" },
                { content: "네, 다음 단계 진행하겠습니다.", time: "07:40 AM" }
            ]
        },
        { 
            id: "User6", 
            recentMessage: "내일 미팅 가능하신가요?", 
            messages: [
                { content: "내일 미팅 가능하신가요?", time: "03:00 PM" },
                { content: "네, 가능합니다.", time: "03:05 PM" },
                { content: "그럼 내일 10시로 잡겠습니다.", time: "03:10 PM" }
            ]
        },
        { 
            id: "User7", 
            recentMessage: "새로운 디자인 초안을 확인해주세요.", 
            messages: [
                { content: "새로운 디자인 초안을 확인해주세요.", time: "11:00 AM" },
                { content: "확인해보겠습니다.", time: "11:05 AM" },
                { content: "수정사항을 메일로 보내겠습니다.", time: "11:10 AM" }
            ]
        },
        { 
            id: "User8", 
            recentMessage: "프로젝트 마감일이 언제인가요?", 
            messages: [
                { content: "프로젝트 마감일이 언제인가요?", time: "04:15 PM" },
                { content: "다음 주 금요일입니다.", time: "04:20 PM" },
                { content: "알겠습니다. 일정에 맞춰 진행하겠습니다.", time: "04:25 PM" }
            ]
        }
    ];

    // 페이지 로드 시 샘플 데이터로 사용자 목록 업데이트
	document.addEventListener("DOMContentLoaded", function() {
	    // 사용자 목록 업데이트
	    updateUserList(sampleUsers);
	});
    
	function updateUserList(users) {
	    const userList = document.getElementById("userList");
	    userList.innerHTML = ""; // 기존 목록 초기화
	    users.forEach(user => {
	        const userItem = document.createElement("div");
	        userItem.classList.add("user-item");
	        userItem.onclick = function() {
	            showChatHistory(user);
	        };
	
	        const userId = document.createElement("div");
	        userId.classList.add("user-id");
	        userId.textContent = user.id; // 사용자 ID 표시
	
	        const recentMessage = document.createElement("div");
	        recentMessage.classList.add("recent-message");
	        recentMessage.textContent = user.recentMessage; // 가장 최근 메시지 표시
	
	        userItem.appendChild(userId);
	        userItem.appendChild(recentMessage);
	        userList.appendChild(userItem);
	    });
	}
	function showChatHistory(user) {
	    const chatBox = document.getElementById("chatBox");
	    chatBox.innerHTML = ""; // 기존 채팅 내역 초기화

	    // 채팅 메시지 추가
	    user.messages.forEach(message => {
	        const messageItem = document.createElement("div");
	        messageItem.classList.add("message-item");

	        const messageContent = document.createElement("div");
	        messageContent.classList.add("message-content");
	        messageContent.textContent = message.content; // 메시지 내용 표시

	        const messageTime = document.createElement("span");
	        messageTime.classList.add("message-time");
	        messageTime.textContent = message.time; // 메시지 시간 표시

	        messageItem.appendChild(messageContent);
	        messageItem.appendChild(messageTime);
	        chatBox.appendChild(messageItem);
	    });

	    // 기존 입력 박스를 제거 (있을 경우)
	    const existingInputBox = document.getElementById("dynamicChatInputBox");
	    if (existingInputBox) {
	        existingInputBox.remove();
	    }

	    // 채팅 입력 박스를 동적으로 생성
	    const chatInputBox = document.createElement("div");
	    chatInputBox.classList.add("chat-input-box", "d-flex", "mt-2");
	    chatInputBox.id = "dynamicChatInputBox";

	    const inputField = document.createElement("input");
	    inputField.type = "text";
	    inputField.id = "chatInput";
	    inputField.classList.add("form-control");
	    inputField.placeholder = "메시지를 입력하세요...";

	    const sendButton = document.createElement("button");
	    sendButton.classList.add("btn", "btn-primary", "ml-2");
	    sendButton.textContent = "전송";
	    sendButton.onclick = function() {
	        sendMessage(user); // sendMessage 함수 호출 시 사용자 정보 전달
	    };

	    // 입력 필드와 버튼을 입력 박스에 추가
	    chatInputBox.appendChild(inputField);
	    chatInputBox.appendChild(sendButton);

	    // 채팅 박스 아래에 입력 박스를 추가
	    chatBox.parentNode.appendChild(chatInputBox);

	}

	function sendMessage(user) {
	    const inputField = document.getElementById("chatInput");
	    const messageContent = inputField.value.trim();

	    if (messageContent === "") return; // 빈 메시지는 보내지 않음

	    // 새 메시지 생성
	    const messageItem = document.createElement("div");
	    messageItem.classList.add("message-item");

	    const messageDiv = document.createElement("div");
	    messageDiv.classList.add("message-content");
	    messageDiv.textContent = messageContent; // 입력된 메시지 내용 표시

	    const messageTime = document.createElement("span");
	    messageTime.classList.add("message-time");
	    const currentTime = new Date().toLocaleTimeString(); // 현재 시간 표시
	    messageTime.textContent = currentTime;

	    messageItem.appendChild(messageDiv);
	    messageItem.appendChild(messageTime);

	    // 채팅 박스에 새 메시지 추가
	    const chatBox = document.getElementById("chatBox");
	    chatBox.appendChild(messageItem);

	    // 입력 필드 초기화
	    inputField.value = "";

	    // 새 메시지를 사용자 객체에 추가 (추가로 필요할 경우)
	    user.messages.push({ content: messageContent, time: currentTime });

	    console.log('메시지 전송:', messageContent);
	}
</script>
</body>
</html>
