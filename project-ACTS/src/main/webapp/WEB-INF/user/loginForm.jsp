<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Acts On</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
   function login() {
      if (form.accountId.value == "") {
         alert("사용자 ID를 입력하십시오.");
         form.accountId.focus();
         return false;
      } 
      if (form.password.value == "") {
         alert("비밀번호를 입력하십시오.");
         form.password.focus();
         return false;
      }      
      form.submit();
   }
   
   function userCreate(targetUri) {
      form.action = targetUri;
      form.method="GET";      // register form 요청
      form.submit();
   }
   </script>
<style>
#headLine {
	background-color: #b5cda3;
	display: grid;
	grid-template-columns: 8fr 1fr;
	margin: 100px, 30px, 500px, 30px;
	height: 50px;
	width: auto;
}

#headLineText {
	padding-left: 20px;
	padding-top: 5px;
	color: white;
	font-size: 30px;
	font-weight: bold;
}

</style>
</head>
<body>
	<!--<script type="text/javascript" src="js/bootstrap.js"></script>  -->

	<div id="headLine" align="center">
		<div id="headLineText" align="left">
			<a href="<c:url value='/comm/main'></c:url>"  id="headLineText">
                Acts <img src="<c:url value='/images/sprout2.png' />" border="0" width="45" height="30"/></a>
		</div>
	</div>


<div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="form" class="form" action="<c:url value='/user/login' />" method="post">
                            <h3 class="text-center text-info">Login</h3>
		   <c:if test="${loginFailed}">
			<font color="red"><c:out value="${exception.getMessage()}" /></font>
		   </c:if>
                            <div class="form-group">
                                <label for="username" class="text-info">Username:</label><br>
                                <input type="text" name="accountId" id="accountId" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <input type="submit" name="login" class="btn btn-info btn-md" value="login" onClick="login()">
                                <input type="button" name="join" class="btn btn-info btn-md" value="join" onClick="userCreate('<c:url value='/user/register/form' /> ')">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>



</html>