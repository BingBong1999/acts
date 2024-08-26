<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Post> postList = (List<Post>)request.getAttribute("postList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ACTS</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
        .card {
            margin-bottom: 30px;
        }
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .card-title {
            font-size: 1.25rem;
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .category-badge {
            font-size: 0.9rem;
            font-weight: 700;
            margin-left: 10px;
            color: #007bff;
        }
        .price {
            font-size: 1.25rem;
            font-weight: 700;
            margin-bottom: 10px;
        }
        .author {
            font-size: 0.9rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <%@include file="/WEB-INF/navbar.jsp"%>
    <div class="container mt-4">
        <div class="row">
            <c:forEach var="post" items="${postList}">
                <div class="col-md-4">
                    <div class="card">
                        <img src="${post.imgUrl}" class="card-img-top" alt="${post.title}">
                        <div class="card-body">
                            <h5 class="card-title">
                                ${post.title}
                                <span class="category-badge badge badge-info">${post.categoryId}</span>
                            </h5>
                            <p class="price">${post.price}원</p>
                            <p class="author">작성자: ${post.writerId}</p>
                            <a href="<c:url value='/post/${post.postId}' />" class="btn btn-primary">상세보기</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
