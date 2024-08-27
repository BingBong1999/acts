<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<%
	String userId = (String) request.getAttribute("userId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ACTS - 다양한 주니어들과 소비자들의 아이디어와 열정이 모이는 곳</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>
	<script>
		function postCreate() {
			if (form.title.value == "") {
				alert("제목을 입력하십시오.");
				form.title.focus();
				return false;
			}
			if (form.price.value == "") {
				alert("가격을 입력하십시오.");
				form.price.focus();
				return false;
			}
	
			if (form.body.value == "") {
				alert("내용을 입력하십시오.");
				form.content_textarea.focus();
				return false;
			}
			form.submit();
		}
	</script>
	<style>
	        body {
	            font-family: 'Noto Sans KR', sans-serif;
	            background-color: #f8f9fa;
	        }
	        .post-form-container {
	            max-width: 800px;
	            margin: 50px auto;
	            padding: 30px;
	            background: white;
	            border-radius: 8px;
	            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	        }
	        .post-form-container h2 {
	            text-align: left;
	            margin-bottom: 5px;
	        }
	        .post-form-container p {
	            text-align: left;
	            margin-bottom: 30px;
	        }
	        .form-group label {
	            font-weight: bold;
	            text-align: left;
	            display: block;
	        }
	        .form-control {
	            width: 100%;
	        }
	        .btn-primary {
	            background-color: #007bff;
	            border-color: #007bff;
	        }
	        .btn-primary:hover {
	            background-color: #0056b3;
	            border-color: #0056b3;
	        }
    </style>
</head>
<body>

<%@include file="/WEB-INF/navbar.jsp"%>

<div class="post-form-container">
    <h3><%= userId %>님만의 특별한 재능</h3>
    <p style="color: gray;">함께함으로써 세상에 가치를 더하는 첫 발자국, 지금부터 소개해주세요.</p>
    <form name="form" method="POST" action="<c:url value='/post/upload' />" enctype="multipart/form-data">
        <div class="form-group">
            <label for="categoryId">카테고리</label>
            <select class="form-control" name="categoryId">
                <option value="1">예술</option>
                <option value="2">음악</option>
                <option value="3">글쓰기</option>
                <option value="4">디자인</option>
                <option value="5">기술</option>
                <option value="6">기타</option>
            </select>
        </div>
        
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요">
        </div>
        <div class="form-group">
            <label for="body">내용</label>
            <textarea class="form-control" name="body" rows="10" placeholder="내용을 입력하세요"></textarea>
            <script>
                CKEDITOR.replace('body', {
                    filebrowserUploadUrl: '/uploader/upload.php',
                    filebrowserUploadMethod: 'form'
                });
            </script>
        </div>
       
        <div class="form-group">
            <label for="price">가격(원)</label>
            <input type="number" class="form-control" name="price" placeholder="가격을 입력하세요">
        </div>
        
        <button type="submit" onClick="postCreate()" class="btn btn-primary btn-block">등록</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.amazonaws.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
