<%@page import="searchouse.utils.AppUtil"%>
<%@page import="searchouse.bean.ErrorBean"%>
<%@page import="searchouse.bean.AccountBean"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String username = "";
	String password = "";
	ErrorBean err = null;
	if(AppUtil.getData(session, "ERROR")!=null){
		err = (ErrorBean) AppUtil.getData(session, "ERROR");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Hind:400,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Slick -->
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link rel="icon" href="./img/logo5.png" type="image/png" />
<title>Searchouse: Log In</title>
</head>
<body>
	<%
		if (request.getSession().getAttribute("LOGINED_USER") != null)
			response.sendRedirect("/searchouse/index");
	%>
	
	
	<div id="header" style="height: 130px">
		<div class="container">
			<div class="pull-left" style="width:85%">
				<!-- Logo -->
				<div class="header-logo" >
					<a class="logo-header" href="/searchouse/index"> <img
						src="img/Logo6.png" width=250 height =100 alt="">
					</a>	
				</div>
				<!-- /Logo -->
			</div>
		</div>
	</div>
		<div class="col-md-3"
			style="margin-top: 3%; margin-left: 25%; margin-right: 25%">
			<h4 class="text-uppercase" align="center">Login</h4>
			<form name="signin" class="review-form" action="/searchouse/LogIn" method="post" autocomplete="off">
				<!--inserire robe giuseppe-->
				<div class="form-group">
					<input class="input" type="text" value="<%=username%>"
						name="username" placeholder="Enter Username" required />
				</div>
				<div class="form-group">
					<input class="input" style="float: left" type="password" value="<%=password%>" name="password"
					placeholder="Enter Password" required />
					<img src= "img/mostra_pass.png" id = "imgPassword" onmouseover="hoverDiv()" onmouseout="outDiv()" onclick="show()" style="margin-top:0.5%; cursor: pointer;height:25px;width: 25px; float: left;">
				</div>
				<div class="form-group" style="margin-top: 10%">
					<input class="input" type="hidden" name="redirectId" />
					<!--value=request.getParameter("redirectId")d") %>" -->
				</div>
				<button class="primary-btn">Login</button>
				<a href="signUpForm.jsp" style="margin-left: 1%">Not a user yet?
					Join us!</a>
			</form>
		</div>
</body>

<%if(err!=null){ 
	AppUtil.removeData(session, "ERROR");%>
	<script type="text/javascript">
	setTimeout(function(){ alert("<%=err.getMessage()%>"); }, 100);
	</script>
<%} %>

<script type="text/javascript">
var cont = 0;
function hoverDiv() {
		document.getElementById('imgPassword').style.backgroundColor = "#c2c2c2";
}
function outDiv() {
	if(cont%2==0)
		document.getElementById('imgPassword').style.backgroundColor = "";
}
function show() {			
	if(cont%2==0){
		document.signin.password.type = "text";
		document.getElementById('imgPassword').style.backgroundColor = "#c2c2c2";
		cont++;
	}
	else
	{
		document.signin.password.type = "password";
		document.getElementById('imgPassword').style.backgroundColor = "";
		cont++;
	}
}
</script>
</html>