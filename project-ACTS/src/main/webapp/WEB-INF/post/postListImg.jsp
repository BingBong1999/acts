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
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />


<style>
.listImg {
  width: 300px;
  height: 150px;
  object-fit: cover;
}
</style>
</head>
<body>


		<%
			if (postList != null) {	
	  			Iterator<Post> postIter = postList.iterator();
	
	 			//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	 			int i = 0;
		%>
		
		<c:forEach var="post" items="${postList}">
		<% if (i % 4 == 0) { %>
				<br> <% } i++; %>
		<a href="<c:url value='/post/sellerPostInfo'>
						<c:param name='postId' value='${post.postId}'/>
					   	<c:param name='writerId' value ='${post.writerId}' />
					   	<c:param name='likeRequest' value = '-1' /> 
				 </c:url>">
						<c:if test="${empty post.getImgUrl()}">
							<img src="<c:url value='/images/noImage.png' />" class="listImg">
						</c:if>
						<c:if test="${not empty post.getImgUrl()}">
							<img src="${pageContext.request.contextPath}/upload/${post.getImgUrl()}" class="listImg">
						</c:if>
		</a>
			
		</c:forEach>
		
		<%
			}
		%>

</body>