<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//User user = (User)request.getAttribute("user");
	@SuppressWarnings("unchecked") 
	List<Transaction> transactionList = (List<Transaction>)request.getAttribute("transactionList");
	int userId = (int) request.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/navbar.jsp" %>
<%@include file="/WEB-INF/myPageNav.jsp" %>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <a class="nav-item nav-link" href="<c:url value='/user/myPage/myBuyerTransaction'>
			<c:param name='userId' value='${userId}'/></c:url>">의뢰건</a>
	       <a class="nav-item nav-link active" href="<c:url value='/user/myPage/mySellerTransaction'>
			<c:param name='userId' value='${userId}'/></c:url>"><span class="sr-only">접수건</span></a>
	    </div>
	  </div>
	</nav>
	<div class="container my-1">
	현재거래내역(의뢰받은 글)
	
	<table style="background-color: YellowGreen">
		<tr>
			<td width="190" align="center" bgcolor="E6ECDE" height="22">제목</td>
			<td width="200" align="center" bgcolor="E6ECDE">가격</td>
			<td width="200" align="center" bgcolor="E6ECDE">주문 날짜</td>
			<td width="200" align="center" bgcolor="E6ECDE">의뢰자</td>
			<td width="200" align="center" bgcolor="E6ECDE">의뢰 내용</td>
		</tr>
		
		<%
			if (transactionList != null) {	
	  			Iterator<Transaction> transactionIter = transactionList.iterator();
	
	 			//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
		%>
		
		<c:forEach var="transaction" items="${transactionList}">
			<tr>
				<td width="190" align="center" bgcolor="ffffff" height="20">
					<a href="<c:url value='/post/postInfo'>
					   			<c:param name='postId' value='${transaction.post.postId}'/>
					   			<c:param name='writerId' value ='${transaction.post.writerId}' />
					   			<c:param name='setting' value = '-1' /> 
			 		 		 </c:url>">
			  		${transaction.post.title}</a>
				</td>
				
				<td width="200" align="center" bgcolor="ffffff" height="20">
					${transaction.post.price}
				</td>
				
				<td width="200" align="center" bgcolor="ffffff" height="20">
					${transaction.transDate}
				</td>
				<td width="200" align="center" bgcolor="ffffff" height="20">
					${transaction.user.nickName}
				</td>
				<td width="190" align="center" bgcolor="ffffff" height="20">
					<a href="<c:url value='/user/transactionInfo'>
					   			<c:param name='transId' value='${transaction.transId}'/></c:url>">${transaction.transTitle}</a>
				</td>
			</tr>
		</c:forEach>
		
		<%
			}
		%>
	</table>
	</div>
</body>
</html>