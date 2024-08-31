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
    <title>ACTS - 다양한 주니어들과 소비자들의 아이디어와 열정이 모이는 곳</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
        .card-img-container {
		    padding: 15px;
		}
        .card-img-top {
        	height: 220px;
        	width: auto;
        }
        .card {
        	height: 450px;
            margin-bottom: 30px;
        }
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .card-title {
            font-size: 1.0rem;
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .category-badge {
            font-size: 0.9rem;
            font-weight: 700;
            color: #007bff;
            margin-right: 5px;
        }
        .price {
            font-size: 0.9rem;
            font-weight: 700;
            margin-bottom: 10px;
        }
        .author {
            font-size: 0.9rem;
            color: #6c757d;
        }
        .add-post-btn {
        	
            position: absolute;
            top: 20px;
            right: 300px;
            width: 50px;
            height: 50px;
            background-color: #007bff;
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 28px;
            cursor: pointer;
            text-decoration: none;
        }
        .container-relative {
            position: relative;
        }
        .add-post-btn.chat-btn {
		    top: 80px; /* Adjust this value as needed */
		    right: 300px; /* Same as the '+' button to align properly */
		    background-color: #28a745; /* Green theme similar to Bootstrap success button */
		}
    </style>
</head>
<body>
    <%@include file="/WEB-INF/navbar.jsp"%>

    <div class="container-relative">
        <a href="<c:url value='/post/form' />" class="add-post-btn">+</a>
        <a href="<c:url value='/chat/list' />" class="add-post-btn chat-btn"><i class="bi bi-chat-dots"></i></a>
    </div>

    <div class="container mt-4">
        <div class="row">
            <c:forEach var="post" items="${postList}">
                <div class="col-md-3">
                    <div class="card">
                    	<c:if test="${empty post.getImageUrl()}">
                    		<div class="card-img-container">
								<img src="<c:url value='/images/noImage.png' />" class="card-img-top">
							</div>
						</c:if>
						<c:if test="${not empty post.getImageUrl()}">
 							<div class="card-img-container">
                            	<img src="${pageContext.request.contextPath}/imageResource/${post.imageUrl.get(0)}" class="card-img-top">
                        	</div>
						</c:if>
                       
                        <div class="card-body">
                            <h5 class="card-title">
                                <span class="category-badge badge badge-info">${post.categoryName}</span>
                                ${post.title}
                            </h5>
                            <p class="price">${post.price}원</p>
                            <p class="author">작성자: ${post.authorId}</p>
                            
                            <a href="<c:url value='/post/postInfo'>
                                        <c:param name='postId' value='${post.id}'/>
                                        <c:param name='writerId' value ='${post.authorId}' />
                                        <c:param name='likeRequest' value = '-1' /> 
                                    </c:url>" class="btn btn-primary"> 상세보기
                            </a>
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
