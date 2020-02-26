<%@page import="searchouse.bean.AccountBean"%>
<%@page import="searchouse.utils.AppUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	AccountBean account = new AccountBean();
	boolean logged = false;
	if (request.getSession() != null && request.getSession().getAttribute("LOGINED_USER") != null) {
		account = AppUtil.getLoginedUser(session);
		logged = account.isLogged();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Hind:400,700"
	rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />
<link rel="stylesheet" href="css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>


	<!-- header -->
	<div id="header">
		<div class="container">
			<div class="pull-left" style="width:85%">
				<!-- Logo -->
				<div class="header-logo" >
					<a class="logo-header" href="/searchouse/index"> <img
						src="img/Logo6.png" width=250 height =100 alt="">
					</a>
					<a class= "logo-header" href="/searchouse/maps.jsp">
						<img src= "img/map.png" width =40 height=40 alt="">
					</a>	
				</div>
				<!-- /Logo -->
			</div>
			
			<div style="float: right; margin-top:2%; margin-right:2%">
				<strong><a href="myProfile.jsp"> My Account </a></strong>
				<%if (!logged) { %>
				<div>
				<a href="logInForm.jsp">Login</a> / <a href="signUpForm.jsp">Join</a>
				</div>
				<%} else { %>
				<%response.setHeader("cache-control", "no-cache,no-store,must-revalidate");%>
				<div style="color:#FFFFFF">
					Benvenuto: <%=account.getUsername()%>
				</div>
				<a href="LogOut">Logout</a>
				<%}%>
			</div>
		
			<% if (logged) {%>
					<%@ include file="LoggedHeader.jsp" %> 
			<%}%>
		</div>
	</div>
	<!-- header -->
</html>