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
            font-family: 'Noto Sans KR';
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
            position: relative;
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
		.no-messages {
		    text-align: center;
		    color: #6c757d;
		    margin-top: 20px;
		    font-size: 1rem;
		}
		.timestamp {
		    font-family: 'Noto Sans KR';
		    font-size: 0.8rem;
		    color: #6c757d;
		    top: 0; 
		    right: 0;
		    margin-right: 3px;
		    margin-top: 23px;
		    position: absolute;
		}
		.text-muted {
        	margin-top: 200px;
		    justify-content: center;
		    align-items: center;
		    height: 100%; 
		    text-align: center;
        }
		
		
		
		.chat-box {
		    height: 530px; /* 높이를 고정 */
		    overflow-y: auto; /* 내용이 넘칠 경우 스크롤 가능 */
		    padding: 10px;
		    background-color: #fff;
		    border: 1px solid #dee2e6;
		    border-radius: 8px;
		    margin-bottom: 20px;
		}
        .sent-message {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 10px;
            position: relative; 
        }

        .received-message {
            display: flex;
            justify-content: flex-start;
            margin-bottom: 10px;
            position: relative; 
        }

        .message-content {
            padding: 10px;
            border-radius: 5px;
            background-color: #f1f1f1;
            display: inline-block;
            max-width: 80%;
            position: relative;
        }

        .message-time {
            font-size: 0.8rem;
            color: #6c757d;
        	margin-top: auto; /* 타임스탬프를 아래쪽에 붙임 */
    		margin-left: 8px; /* 기본 여백 */
        }

        .chat-input-box {
            display: flex;
            margin-top: 10px;
        }

        .chat-input-box input {
            flex: 1;
            border-radius: 4px;
            margin-right: 10px;
        }

        .chat-input-box button {
            white-space: nowrap;
        }
        .sent-message .message-time {
		    order: -1; /* 타임스탬프를 메시지 왼쪽에 배치 */
		    margin-right: 8px;
		    margin-left: 0;
		}
		
		.received-message .message-time {
		   	margin-left: 8px;
		}
		.sent-message .message-content {
		    background-color: #007bff;
    		color: white;
		}
		.date-separator {
		    display: flex;
		    align-items: center;
		    text-align: center;
		    color: #adb5bd;
		    margin: 20px 0;
		  
		    position: relative;
		}
		
		.date-separator::before, .date-separator::after {
		    content: "";
		    flex: 0.9;
		    border-bottom: 2px solid #dee2e6;
		}
		
		.date-separator::before {
		    margin-right: 10px;
		}
		
		.date-separator::after {
		    margin-left: 10px;
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
        	<%@include file="/WEB-INF/chat/history.jsp"%>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>

$(document).ready(function() {
    updateChatList();
});

function updateChatList() {
    $.ajax({
        url: '/project-ACTS/chat/list',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
        	
        	const messageList = response;
        	
            if (messageList.length === 0) {
            	$('#userList').innerHTML = "<p class='text-muted'>현재 대화하는 사용자가 없습니다.</p>";
                return;
            }

            messageList.forEach(Message => {
            	
                const user_item = document.createElement("div");
                user_item.classList.add("user-item");
                user_item.onclick = function() {
                	updateChatHistory(Message.receiverId);
                };
                
                const user_id = document.createElement("div");
                user_id.classList.add("user-id");
                user_id.textContent = Message.receiverId;
                
                const messageDate = new Date(Message.createdAt).toLocaleDateString('ko-KR', {
        	        month: 'long',
        	        day: 'numeric'
        		});
                
                const timestamp = document.createElement("div");
                timestamp.classList.add("timestamp");
                timestamp.textContent = messageDate;

                const recent_message = document.createElement("div");
                recent_message.classList.add("recent-message");
                recent_message.textContent = Message.content;
                
                
                user_item.appendChild(user_id);
                user_item.appendChild(timestamp);
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
