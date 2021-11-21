<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked") 
	List<Post> postList = (List<Post>)request.getAttribute("postList");
%>
<html>
<head>
<title>게시물 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />"
	type="text/css">
</head>
<body>

	<table style="background-color: YellowGreen">
		<tr>
			<td width="190" align="center" bgcolor="E6ECDE" height="22">제목</td>
			<td width="200" align="center" bgcolor="E6ECDE">가격</td>
			<td width="200" align="center" bgcolor="E6ECDE">구매자/판매자</td>
			<td width="200" align="center" bgcolor="E6ECDE">작성자</td>
		</tr>
		
		<%
			if (postList != null) {	
	  			Iterator<Post> postIter = postList.iterator();
	
	 			//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
		%>
		
		<c:forEach var="post" items="${postList}">
			<tr>
				<td width="190" align="center" bgcolor="ffffff" height="20">
					<a href="<c:url value='/post/postInfo'>
					   			<c:param name='postId' value='${post.postId}'/>
					   			<c:param name='writerId' value ='${post.writerId}' />
			 		 		 </c:url>">
			  		${post.title}</a>
				</td>
				
				<td width="200" align="center" bgcolor="ffffff" height="20">
					${post.price}
				</td>
				
				<td width="200" align="center" bgcolor="ffffff" height="20">
					${post.pType}
				</td>
				<td width="200" align="center" bgcolor="ffffff" height="20">
					${post.writerId}
				</td>
			</tr>
		</c:forEach>
		
		<%
			}
		%>
	</table>
</body>
</html>