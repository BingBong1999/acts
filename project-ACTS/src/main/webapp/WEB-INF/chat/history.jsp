<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>

<div class="chat-box" id="chatBox">
    <p class="text-muted">채팅 내역을 불러오고 있습니다...</p>
</div>

<div class="chat-input-box" id="dynamicChatInputBox">
    <input type="text" id="chatInput" class="form-control" placeholder="메시지를 입력하세요.">
    <button class="btn btn-primary" id="sendButton">전송</button>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>

	let socket;
	let loginId;
	
    function updateChatHistory(paramReceiverId) {
    	
    	$.ajax({
            url: '/project-ACTS/chat/history',
            method: 'GET',
            data: { 
                receiverId: paramReceiverId
            },
            dataType: 'json',
            success: function(response) {
            	
            	const messageList = response.messageList;
            	loginId = response.loginId;
            	
            	const chatBox = document.getElementById("chatBox");
            	chatBox.innerHTML = "";

            	if (messageList.length === 0) {
            		chatBox.innerHTML = "<p class='text-muted'>대화내역이 없습니다.</p>";
                    return;
                }

            	let lastDate = '';
            	
            	messageList.forEach(msg => {
            		
            		const messageDate = new Date(msg.createdAt).toLocaleDateString('ko-KR', {
                        month: 'long',
                        day: 'numeric'
                    });
                    
            		displayMessage(msg, lastDate);
                    
            		lastDate = messageDate;
            		
            	});
            	
            	$('#sendButton').off('click').on('click', function () {
                    sendMessage(paramReceiverId);
                });
            	
            	connectWebSocket();
            	
            },
            error: function(error) {
            	 console.error('서버 응답 오류:', error);
            }
        });
    	
    }
    
    function connectWebSocket() {
        socket = new WebSocket("ws://localhost:8080/project-ACTS/chatSocket?userId=" + loginId);

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
    
    function displayMessage(message, lastDate) {
    	
    	const messageDate = new Date(message.createdAt).toLocaleDateString('ko-KR', {
    	        month: 'long',
    	        day: 'numeric'
    	});
    	
        if (messageDate !== lastDate) {
            const dateSeparator = document.createElement("div");
            dateSeparator.classList.add("date-separator");
            dateSeparator.textContent = messageDate;
            chatBox.appendChild(dateSeparator);
        }
    	
    	const messageItem = document.createElement("div");
        
        if (message.senderId === loginId) {
        	messageItem.classList.add("sent-message");
        } else {
        	messageItem.classList.add("received-message");
        }

        const messageContent = document.createElement("div");
        messageContent.classList.add("message-content");
        messageContent.textContent = message.content;

        const messageTimeValue = new Date(message.createdAt).toLocaleTimeString('ko-KR', {
	        hour: '2-digit',
	        minute: '2-digit'
		});
        
        const messageTime = document.createElement("span");
        messageTime.classList.add("message-time");
        messageTime.textContent = messageTimeValue;

        messageItem.appendChild(messageContent);
        messageItem.appendChild(messageTime);

        chatBox.appendChild(messageItem);
        chatBox.scrollTop = chatBox.scrollHeight;
        
    }
    
    function sendMessage(receiverId) {
    	
        const inputField = document.getElementById("chatInput");
        const messageContent = inputField.value.trim();

        if (messageContent === "") 
        	return;

        const message = {
            senderId: loginId,
            receiverId: receiverId,
            content: messageContent,
            createdAt: new Date().toISOString()
        };

        socket.send(JSON.stringify(message));
        inputField.value = ""; 
    }
</script>

</body>

