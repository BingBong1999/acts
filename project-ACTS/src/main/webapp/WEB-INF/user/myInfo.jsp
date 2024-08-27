<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% User user = (User)request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />"
   type="text/css">
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>내 정보</title>
<script>
function userDelete() {
	var flag = confirm('정말 계정을 삭제하시겠습니까?');
   if(flag)
	   form.submit;
   else
	   return false;
}

</script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<%@include file="/WEB-INF/myPageNav.jsp" %>
<div class="container my-1">
<h3 align = "left">내 정보</h3>
	<form method="POST" action="<c:url value='/user/delete' />">
	<table>

		<tr><td>아이디</td>
			<td><%= user.getId() %></td></tr>
		<tr><td>이메일</td>
			<td><%= user.getEmail() %></td></tr>

	</table>
	<input type="hidden" name="accountId" value="${user.accountId}" />
	<div class="form-row float-right">
			<input type="submit" onclick="userDelete()" value="회원 탈퇴" class="btn btn-secondary" id="advance-set-submit" />
	</div>
	
   	</form>
   	
	<!-- <input type="button" value="이전으로 돌아가기" onClick="history.go(-1)"> -->
	</div>
</body>
</html>