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
		.no-messages {
		    text-align: center;
		    color: #6c757d;
		    margin-top: 20px;
		    font-size: 1rem;
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

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>

$(document).ready(function() {
    // 페이지가 로드되면 서버에 요청을 보냅니다.
    updateChatList();
});

function updateChatList() {
    $.ajax({
        url: '/project-ACTS/chat/list',
        method: 'GET',
        data: { action: 'list' },
        dataType: 'json',
        success: function(messageList) {
        	
            $('#userList').empty();
        	
            if (messageList.length === 0) {
            	$('#userList').innerHTML = "<p class='text-muted'>현재 대화하는 사용자가 없습니다.</p>";
                return;
            }

            messageList.forEach(Message => {
            	
                const user_item = document.createElement("div");
                user_item.classList.add("user-item");
                user_item.onclick = function() {
                    showChatHistory();
                };

                const user_id = document.createElement("div");
                user_id.classList.add("user-id");
                user_id.textContent = Message.receiverId;
                
                const recent_message = document.createElement("div");
                recent_message.classList.add("recent-message");
                recent_message.textContent = Message.content;


                user_item.appendChild(user_id);
                user_item.appendChild(recent_message);
                $('#userList').append(user_item);
            });
        },
        error: function(error) {
        	 console.error('서버 응답 오류:', error);
        }
    });
}

</script>
</body>
</html>
