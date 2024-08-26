<%@page contentType="text/html; charset=utf-8"%>
<%@page import="model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%

Post post = (Post) request.getAttribute("post");
String nickName = (String) request.getAttribute("nickname");

User user = (User) request.getAttribute("user");
String writerId = (String) request.getParameter("writerId");
String setting = (String) request.getAttribute("setting");
List<Review> reviewList = (List<Review>) request.getAttribute("reviewList");

%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />"
	type="text/css">

<style>
.Img {
	width: 600px;
	height: 300px;
	object-fit: cover;
}
</style>

</head>
<body>

	<%@include file="/WEB-INF/navbar.jsp"%>

	<br>
	<table style="width: 100%">
		<tr>
			<td width="20"></td>
			<td>
				<table>
					<tr>
						<td>판매 관련 게시글 / 작품번호:<%=post.getPostId()%> / 작가:<%=nickName%></td>
					</tr>
					<tr>
						<td class="title">&nbsp;&nbsp;<h2><%=post.getTitle()%></h2>&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td>조회수: <%=post.getViews()%></td>
					</tr>
					<tr>
						<td>등록 시간: <%=post.getcTime()%></td>
					</tr>
					<tr>
						<td>가격: <%=post.getPrice()%>원</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>

				<table>
					<tr>
						<c:if test="${empty post.getImgUrl()}">
							<img src="<c:url value='/images/noImage.png' />" class="Img">
						</c:if>
						
						<c:if test="${not empty post.getImgUrl()}">
							<img src="${pageContext.request.contextPath}/upload/${post.imgUrl}" class="Img">
						</c:if>
					</tr>
					<tr>
						<td><br><h4>${post.desc}</h4></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br>
	<h2>댓글 리스트</h2>
	<table>
		<c:forEach var="review" items="${reviewList}">
			<tr>
				<td width="100">${review.user.nickName}</td>
				<td width="100">
					<c:if test="${review.score eq '1'}"> ★ </c:if> 
					<c:if test="${review.score eq '2'}"> ★★ </c:if> 
					<c:if test="${review.score eq '3'}"> ★★★ </c:if> 
					<c:if test="${review.score eq '4'}"> ★★★★ </c:if> 
					<c:if test="${review.score eq '5'}"> ★★★★★ </c:if>
				</td>
				<td width="300">${review.content}</td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<a href="<c:url value='/post/sellerPostUpdate'>
				<c:param name='postId' value='${post.postId}'/>
			  </c:url>">게시글수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<a href="<c:url value='/post/delete'>
				<c:param name='postId' value='${post.postId}'/>
				<c:param name='writerId' value='${post.writerId}'/>
			 </c:url>">게시글 삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<a href="<c:url value='/post/transactionForm'>
				<c:param name='postId' value='${post.postId}'/>
				<c:param name='writerId' value='${post.writerId}'/>
			 </c:url>">주문하기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<c:if test="${postUpdateFailed || deleteFailed}">
		<font color="red"><c:out value="${exception.getMessage()}" /></font>
	</c:if>

	<c:if test="${trasactionCreateSuccess}">
	    	<script>
				alert("주문이 완료되었습니다.");
			</script>
	   </c:if>
	   <c:if test="${trasactionCreateFailed}">
	    	<script>
				alert("본인이 작성한 글입니다.");
			</script>
	   </c:if>

	<a href="<c:url value='/review/upload/form'>
							<c:param name='postId' value='${post.postId}'/>
			 </c:url>">후기작성</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<c:if test="${empty user.userId}">
		<form method="post" action="<c:url value='/user/login' />">
			<button type="submit">좋아요</button>
		</form>
	</c:if>
	<c:if test="${isLiked eq false and not empty user.userId}">
		<form method="POST" action="<c:url value='/post/sellerPostInfo' />">
			<input type="hidden" name="postId" value='${post.postId}'> <input
				type="hidden" name="userId" value='${user.userId}'> <input
				type="hidden" name="setting" value='1'> <input type="hidden"
				name="writerId" value='<%=writerId%>'>

			<button type="submit">좋아요</button>
		</form>
	</c:if>

	<c:if test="${isLiked eq true}">
		<form method="POST" action="<c:url value='/post/sellerPostInfo' />">
			<input type="hidden" name="postId" value='${post.postId}'> <input
				type="hidden" name="userId" value='${user.userId}'> <input
				type="hidden" name="setting" value='0'> <input type="hidden"
				name="writerId" value='<%=writerId%>'> <input type="hidden"
				name="hey" value='hey'>

			<button type="submit">좋아요 해제</button>
		</form>
	</c:if>

</body>
</html>