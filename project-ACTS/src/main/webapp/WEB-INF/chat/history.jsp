<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>

<div class="chat-box" id="chatBox">
    <p class="text-muted">채팅 내역을 불러오고 있습니다...</p>
</div>

<div class="chat-input-box" id="dynamicChatInputBox">
    <input type="text" id="chatInput" class="form-control" placeholder="메시지를 입력하세요...">
    <button class="btn btn-primary" id="sendButton">전송</button>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>

    let socket;
    let loginId;
    let receiverId;
    
    // 웹소켓 서버에 연결
    function connectWebSocket() {
        socket = new WebSocket("ws://localhost:8080/project-ACTS/chatSocket");

        socket.onopen = function() {
            console.log("웹소켓 연결이 열렸습니다.");
        };

        socket.onmessage = function(event) {
            const message = JSON.parse(event.data);
            displayMessage(message);
        };

        socket.onclose = function() {
            console.log("웹소켓 연결이 닫혔습니다.");
        };

        socket.onerror = function(error) {
            console.error("웹소켓 오류:", error);
        };
    }

    function displayMessage(msg, loginId) {
    	
        const chatBox = document.getElementById("chatBox");
        chatBox.innerHTML = "";

        const messageItem = document.createElement("div");
        
        if (msg.senderId === loginId) {
            messageItem.classList.add("sent-message");
        } else {
            messageItem.classList.add("received-message");
        }

        const messageContent = document.createElement("div");
        messageContent.classList.add("message-content");
        messageContent.textContent = msg.content;

        const messageTime = document.createElement("span");
        messageTime.classList.add("message-time");
        messageTime.textContent = msg.createdAt;

        messageItem.appendChild(messageContent);
        messageItem.appendChild(messageTime);

        chatBox.appendChild(messageItem);
        chatBox.scrollTop = chatBox.scrollHeight;
    }

    // 메시지 전송
    function sendMessage(receiverId, loginId) {
        const inputField = document.getElementById("chatInput");
        const messageContent = inputField.value.trim();

        if (messageContent === "") return;

        const message = {
            senderId: loginId, // 실제로 현재 사용자 ID를 설정해야 함
            receiverId: receiverId,
            content: messageContent,
            createdAt: new Date().toLocaleTimeString()
        };

        socket.send(JSON.stringify(message)); // 서버로 메시지 전송
        displayMessage(message); // 전송한 메시지도 화면에 표시
        inputField.value = ""; // 입력 필드 초기화
    }

    // 전송 버튼 이벤트 리스너
    $(document).on('click', '#sendButton', function () {
        const receiverId = this.receiverId; // 실제로 상대방 ID를 설정해야 합니다.
        sendMessage(receiverId);
    });

    $(document).ready(function () {
        connectWebSocket();
    });
</script>

</body>

