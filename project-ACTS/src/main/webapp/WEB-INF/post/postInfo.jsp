<%@page contentType="text/html; charset=utf-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Post post = (Post)request.getAttribute("post");
	User user = (User)request.getAttribute("user");
	String nickName = (String)request.getAttribute("nickname");
	String writerId = (String)request.getParameter("writerId");
	String setting = (String)request.getAttribute("setting");
	List<Review> reviewList = (List<Review>) request.getAttribute("reviewList");
%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />"
	type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
	<br>
	<table style="width: 100%">
		<tr>
			<td width="20"></td>
			<td>
				<table>
					<tr>
						<td class="title">&nbsp;&nbsp;<b>게시물 - 상세 게시물 보기 </b>&nbsp;&nbsp;
						</td>
					</tr>

				</table> <br>
				<table style="background-color: YellowGreen">
					<tr>
						<td width="120" align="center" bgcolor="E6ECDE" height="22">제목</td>
						<td width="470" bgcolor="ffffff" style="padding-left: 10">
							${post.title}</td>
					</tr>
					<tr>
						<td width="120" align="center" bgcolor="E6ECDE" height="22">게시물
							작성자 이름</td>
						<td width="470" bgcolor="ffffff" style="padding-left: 10">
							<%=nickName%></td>
					</tr>
					<tr>
						<td width="120" align="center" bgcolor="E6ECDE" height="22">게시글</td>
						<td width="470" bgcolor="ffffff" style="padding-left: 10">
							${post.desc}</td>
					</tr>
					<tr>
						<td width="120" align="center" bgcolor="E6ECDE" height="22">게시글
							종류</td>
						<td width="470" bgcolor="ffffff" style="padding-left: 10">
							${post.pType}</td>
					</tr>
					<tr>
						<td width="120" align="center" bgcolor="E6ECDE" height="22">조회수</td>
						<td width="470" bgcolor="ffffff" style="padding-left: 10">
							${post.views}</td>
					</tr>
					<tr>
						<td width="120" align="center" bgcolor="E6ECDE" height="22">이미지</td>
						<img src="${pageContext.request.contextPath}/upload/${post.imgUrl}">
					</tr>
				</table> <br>

				<a href="<c:url value='/post/update'>
				<c:param name='postId' value='${post.postId}'/>
				</c:url>">게시글 수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				<a href="<c:url value='/post/delete'>
				<c:param name='postId' value='${post.postId}'/>
				<c:param name='writerId' value='${post.writerId}'/>
				</c:url>">게시글 삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				<c:if test="${empty user.userId}">
					<form method="post" action="<c:url value='/user/login' />">
						<button type="submit">
						좋아요
						</button>	
					</form>
				</c:if>
				
				<c:if test="${fOrNot eq 0 and not empty user.userId}">
					<form method="POST" action="<c:url value='/post/postInfo' />">
					  	<input type="hidden" name="postId" value='${post.postId}'>
					  	<input type="hidden" name="userId" value='${user.userId}'>
					  	<input type="hidden" name="setting" value='1'>
					  	<input type="hidden" name="writerId" value='<%=writerId%>'>
						
						<button type="submit">
						좋아요
						</button>	
					</form>
				</c:if>
				
				<c:if test="${fOrNot eq 1}">
					<form method="POST" action="<c:url value='/post/post/postInfo' />">
					  	<input type="hidden" name="postId" value='${post.postId}'>
					  	<input type="hidden" name="userId" value='${user.userId}'>
					  	<input type="hidden" name="setting" value='0'>
					  	<input type="hidden" name="writerId" value='<%=writerId%>'>
					  	<input type="hidden" name="hey" value='hey'>
						
						<button type="submit">
						좋아요 해제
						</button>	
					</form>
				</c:if>
				
				
				<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
		        <c:if test="${postUpdateFailed || deleteFailed}">
			      <font color="red"><c:out value="${exception.getMessage()}" /></font>
			    </c:if>
			    
			    <br><br>
			    <a href="<c:url value='/review/upload/form'>
					<c:param name='postId' value='${post.postId}'/>
				</c:url>">후기 작성</a>
				
			</td>
		</tr>
	</table>
	
	<br><br>
	<h2>댓글 리스트</h2>
	<table class = "table table striped" align = "center"> 
		<!-- 댓글 리스트 -->
		<c:forEach var="review" items="${reviewList}">
			<tr>
				<td width="100" bgcolor="ffffff" height="20">
			  		${review.user.nickName}
				</td>
				
				<td width="100" bgcolor="ffffff" height="20">
					<c:if test="${review.score eq '1'}"> ★ </c:if>
					<c:if test="${review.score eq '2'}"> ★★ </c:if>
					<c:if test="${review.score eq '3'}"> ★★★ </c:if>
					<c:if test="${review.score eq '4'}"> ★★★★ </c:if>
					<c:if test="${review.score eq '5'}"> ★★★★★ </c:if>
				</td>
				
				<td width="300" bgcolor="ffffff" height="20">
					${review.content}
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>