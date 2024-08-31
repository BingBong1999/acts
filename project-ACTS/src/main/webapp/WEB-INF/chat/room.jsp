<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat Application</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Noto Sans KR', sans-serif;
        }
        .chat-container {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .chat-box {
            height: 400px;
            overflow-y: scroll;
            border: 1px solid #dee2e6;
            padding: 10px;
            border-radius: 5px;
            background-color: #f1f1f1;
            margin-bottom: 15px;
        }
        .chat-input {
            display: flex;
        }
        .chat-input input {
            flex: 1;
            border-radius: 0;
        }
        .chat-input button {
            border-radius: 0;
        }
    </style>
</head>
<body>

<div class="chat-container">
    <h2 class="text-center font-weight-bold">채팅창</h2>
    <div class="chat-box" id="chatBox">
        <!-- Messages will appear here -->
    </div>
    <div class="chat-input">
        <input type="text" id="messageInput" class="form-control" placeholder="메시지를 입력하세요.">
        <button class="btn btn-primary" onclick="sendMessage()">Send</button>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    const ws = new WebSocket("ws://localhost:8080/chat");

    ws.onmessage = function(event) {
        const chatBox = document.getElementById("chatBox");
        const message = document.createElement("div");
        message.classList.add("alert", "alert-secondary");
        message.textContent = event.data;
        chatBox.appendChild(message);
        chatBox.scrollTop = chatBox.scrollHeight;
    };

    function sendMessage() {
        const input = document.getElementById("messageInput");
        if (input.value.trim() !== "") {
            ws.send(input.value);
            input.value = '';
        }
    }
</script>

</body>
</html>
