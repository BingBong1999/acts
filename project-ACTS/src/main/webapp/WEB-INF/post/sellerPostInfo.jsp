<%@page contentType="text/html; charset=utf-8"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Post post = (Post) request.getAttribute("post");
String nickName = (String) request.getAttribute("nickname");
User user = (User) request.getAttribute("user");
String writerId = (String) request.getParameter("writerId");
String setting = (String) request.getAttribute("setting");
boolean isLiked = (boolean) request.getAttribute("isLiked");
List<Review> reviewList = (List<Review>) request.getAttribute("reviewList");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>acts - <%=post.getTitle()%></title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap"
	rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">

<style>
body {
	background-color: #f8f9fa;
	font-family: 'Noto Sans KR', sans-serif;
}

.card-title {
	font-weight: 700;
	color: #333;
	display: flex;
	align-items: center;
}

.like-btn, .order-btn, .edit-btn, .delete-btn {
	background: none;
	border: none;
	font-size: 1.2rem;
	cursor: pointer;
	outline: none;
	margin-left: 10px;
}

.like-btn {
	color: red;
}

.order-btn {
	color: #007bff;
	font-weight: 700;
}

.edit-btn, .delete-btn {
	color: #6c757d;
	font-weight: 700;
}

.order-btn:hover, .like-btn:hover, .edit-btn:hover, .delete-btn:hover {
	opacity: 0.7;
}

.like-btn, .order-btn {
	background: none;
	border: none;
	font-size: 0.9rem;
	color: #007bff;
	font-weight: 700;
	cursor: pointer;
	outline: none;
}

.card-text {
	color: #333;
}

h4, h5 {
	font-weight: 700;
	color: #333;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #0056b3;
}

.price-info {
	font-weight: 700;
	color: #333;
	margin-bottom: 15px;
}

.comment-form {
	background-color: #e9ecef;
	padding: 20px;
	border-radius: 10px;
	margin-top: 20px;
	margin-bottom: 20px;
}

.comment-count {
	font-weight: 700;
	margin-bottom: 15px;
}

.form-group {
	margin-bottom: 0;
}

.form-control {
	margin-right: 10px;
}

.btn-submit {
	display: inline-block;
	margin-top: 10px;
	width: 100px;
}

.post-meta {
	color: #6c757d;
	font-size: 0.9rem;
	margin-top: 5px;
}

.post-meta span {
	margin-right: 10px;
}

.img {
	max-width: 400px;
	height: auto;
}

.review-card {
	margin-bottom: 20px;
	border-radius: 10px;
	border: 1px solid #ddd;
}

.review-header {
	font-weight: bold;
	margin-bottom: 10px;
}

.review-content {
	margin-bottom: 10px;
}

.review-time {
	font-size: 0.9em;
	color: #6c757d;
}

.rating-stars {
	color: #f39c12;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/navbar.jsp"%>
	<div class="container mt-5">

		<div class="card">
			<div class="card-body">
				<h2 class="card-title d-flex justify-content-between align-items-center">
					<span><%=post.getTitle()%></span>
					<div class="d-flex align-items-center">
						
						<c:if test="${empty user.userId}">
							<button class="like-btn"
							onclick="window.location.href='<c:url value='/user/login'></c:url>'">
							<i class="bi bi-heart"></i> 좋아요
							</button>
						</c:if>
						
						<c:if test="${not empty user.userId}">
	    					<%
	    						String iconClass = isLiked ? "bi-heart-fill" : "bi-heart";
	    						String likeRequest = isLiked ? "0" : "1";
	   	 					%>
						    <button class="like-btn" onclick="window.location.href='<c:url value='/post/sellerPostInfo'>
						            <c:param name='postId' value='<%= Integer.toString(post.getPostId()) %>'/>
						            <c:param name='userId' value='<%= Integer.toString(user.getUserId()) %>'/>
						            <c:param name='likeRequest' value='<%= likeRequest %>'/>
						            <c:param name='writerId' value='<%= writerId %>'/>
						        </c:url>'">
						        <i class="bi <%= iconClass %>"></i> 좋아요
						    </button>
						</c:if>

						<button class="order-btn ml-2"
							onclick="window.location.href='<c:url value='/post/transactionForm'><c:param name='postId' value='${post.postId}'/><c:param name='writerId' value='${post.writerId}'/></c:url>'">
							<i class="bi bi-cart"></i> 주문하기
						</button>

						<c:if test="${trasactionCreateSuccess}">
							<script>alert("주문이 완료되었습니다.");</script>
						</c:if>
						<c:if test="${trasactionCreateFailed}">
							<script>alert("본인이 작성한 글입니다.");</script>
						</c:if>
					</div>
				</h2>

				<p class="post-meta">
					<span><%=nickName%></span> <span><%=post.getcTime()%></span> <span>👁<%=post.getViews()%></span>
				<div class="d-inline float-right">
					<c:if test="${user != null && user.userId == post.writerId}">
						<button class="edit-btn"
							onclick="window.location.href='<c:url value='/post/sellerPostUpdate'><c:param name='postId' value='${post.postId}'/></c:url>'">
							<i class="bi bi-pencil"></i> 수정
						</button>
						<button class="delete-btn"
							onclick="window.location.href='<c:url value='/post/delete'><c:param name='postId' value='${post.postId}'/><c:param name='writerId' value='${post.writerId}'/></c:url>'">
							<i class="bi bi-trash"></i> 삭제
						</button>
						<c:if test="${postUpdateFailed || deleteFailed}">
							<font color="red"><c:out value="${exception.getMessage()}" /></font>
						</c:if>
					</c:if>
				</div>
				</p>
				<p class="price-info">
					가격:
					<%=post.getPrice()%>원
				</p>
				<hr>
				<c:if test="${empty post.getImgUrl()}">
					<img src="<c:url value='/images/noImage.png' />" class="img">
				</c:if>
				<c:if test="${not empty post.getImgUrl()}">
					<img src="${pageContext.request.contextPath}/upload/${post.imgUrl}"
						class="img">
				</c:if>
				<br> <br>
				<p class="card-text">
					<%=post.getDesc()%>
				</p>
			</div>
		</div>

		<div class="comment-form">
			<form
				action="<c:url value='/review/upload/form'><c:param name='postId' value='${post.postId}'/></c:url>"
				method="post">
				<div class="form-group">
					<textarea class="form-control" id="commentContent" rows="3"
						placeholder="판매자의 상품에 대한 후기를 등록해보세요"></textarea>
				</div>
				<button type="submit" class="btn btn-primary btn-submit">등록하기</button>
			</form>
		</div>

		<div class="container mt-5">
			<div class="mt-4">
				<h3>
					총
					<%=reviewList.size()%>개의 후기가 있습니다.
				</h3>

				<c:if test="${not empty reviewList}">
					<c:forEach var="review" items="${reviewList}">
						<div class="card review-card">
							<div class="card-body">
								<div class="review-header">
									<span>작성자: <strong>${review.user.nickName}</strong></span> <span
										class="review-time ml-3">${review.cTime}</span>
								</div>
								<div class="rating-stars">
									<c:forEach var="i" begin="1" end="${review.score}">
										<i class="fas fa-star"></i>
									</c:forEach>
									<c:forEach var="i" begin="${review.score + 1}" end="5">
										<i class="far fa-star"></i>
									</c:forEach>
								</div>
								<div class="review-content">${review.content}</div>
							</div>
						</div>
					</c:forEach>
				</c:if>

				<c:if test="${empty reviewList}">
					<div class="alert alert-info" role="alert">현재 후기가 없습니다.</div>
				</c:if>
			</div>
		</div>

	</div>

	<!-- Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
        // 좋아요 버튼 클릭 이벤트
        document.querySelector('.like-btn').addEventListener('click', function () {
            const icon = this.querySelector('i');
            if (icon.classList.contains('bi-heart')) {
                icon.classList.remove('bi-heart');
                icon.classList.add('bi-heart-fill'); // 꽉 찬 하트로 변경
                icon.style.color = 'red'; // 하트 색상을 빨간색으로 변경
                icon.style.fontWeight = 'bold'; // 하트 선 굵게
            } else {
                icon.classList.remove('bi-heart-fill');
                icon.classList.add('bi-heart'); // 빈 하트로 변경
                icon.style.color = '#007bff'; // 원래 색상으로 변경
                icon.style.fontWeight = 'normal'; // 원래 선 굵기로 변경
            }
        });
    </script>
</body>
</html>