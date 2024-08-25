<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    text-align: center;margin: 0 auto;width: 100%;
    background-color : white;
}
nav > a {
    float: right;
    margin: auto;
    text-decoration: none;
}
nav div{
    text-align:right;
}
nav div > a{
    text-decoration: none;
    color:black;
}
nav div > a:last-child{
    padding-left:10px;
}
aside >  a{
    text-decoration: none; color:black;
}
#wrap > section > ul > li{
    list-style-type:none;
}
aside {
    width: 30%; float: left;
} 
section {
    float: left;width: 70%
}
#wrap{
    border:1px solid gray; height:50%;
}
.div {
   padding-top: 20px;
   padding-bottom: 20px;
}
</style>
</head>
 

<%
    String pagefile=request.getParameter("page");
    //처음 보여주는 페이지는 내가 쓴글리스트
    if(pagefile==null){
            pagefile="myInfo";
        }
%>
<body>
<%@include file="/WEB-INF/navbar.jsp"%>
<!-- <%@include file="/WEB-INF/myPageNav.jsp" %> -->

</body>

</html>