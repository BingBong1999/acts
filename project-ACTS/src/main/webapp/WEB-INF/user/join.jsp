<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ACTS - 다양한 주니어들과 소비자들의 아이디어와 열정이 모이는 곳</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
        }
        .signup-container {
            text-align: center;
            margin: 50px auto;
            padding: 30px;
        }
        .signup-container h2 {
            margin-bottom: 30px;
        }
        .signup-container form {
            width: 500px;
            margin: 0 auto;
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
            width: 100%;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .bi-house {
            font-size: 3rem;
            color: #000;
        }
        .acts-title {
            font-weight: bold;
            color: #000;
            font-size: 2.5rem;
            margin-bottom: 5px; /* "ACTS"와 문구 사이 간격 조정 */
        }
        .subtitle {
            font-size: 1rem;
            color: #6c757d; /* 회색 글씨 */
            margin-bottom: 20px;
        }
        .login-link {
            margin-top: 20px;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp"%>
<div class="signup-container">
    <div class="acts-title">ACTS</div>
    <div class="subtitle">다양한 주니어들과 소비자들의 아이디어와 열정이 모이는 곳</div>
   
    <form name = "form" method="POST" action="<c:url value='/user/register' />">
        <div class="form-group">
            <label for="username">아이디</label>
            <input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요">
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
        </div>
        <div class="form-group">
            <label for="email">이메일 주소</label>
            <input type="email" class="form-control" name="email" placeholder="이메일 주소를 입력하세요">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">회원가입</button>
    </form>
    <div class="login-link">
        이미 회원이신가요? <a href="<c:url value='/user/login/form' />">로그인</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.amazonaws.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
