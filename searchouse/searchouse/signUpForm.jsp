<%@page import="searchouse.bean.ErrorBean"%>
<%@page import="searchouse.utils.AppUtil"%>
<%@ page import="searchouse.bean.AccountBean"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String name="";
	String surname="";
	String username="";
	String password="";
	String phone="";
	String email="";
	
	ErrorBean err = null;
	if(AppUtil.getData(session, "ERROR")!=null){
		err = (ErrorBean) AppUtil.getData(session, "ERROR");
	}
	
	AccountBean account = new AccountBean();
	if(request.getSession()!=null && request.getSession().getAttribute("REGISTERED_ACCOUNT")!=null){
		account = (AccountBean) AppUtil.getData(session, "REGISTERED_ACCOUNT");
		name = account.getName()+"";
		surname = account.getSurname()+"";
		username = account.getUsername()+"";
		password = "";
		phone = account.getPhone()+"";
		email = account.getEmail()+"";
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script type="text/javascript" src="js/api.js"></script>
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

<script type="text/javascript" src="js/password.js"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="icon" href="./img/logo5.png" type="image/png" />
<title>Searchouse: Sign Up</title>
</head>
<body>

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
			<h4 class="text-uppercase" align="center">Create an account now!</h4>
			<form name="signup" class="review-form" action="signUp" method="post">
		
				<div class="form-group">
					<input class="input" type="text" name="name" value="<%=name%>"
						placeholder="Enter Name (*)" required />
				</div>
				<div class="form-group">
					<input class="input" type="text" name="surname"
						value="<%=surname%>" placeholder="Enter Last Name" required/>
				</div>
				<div class="form-group">
					<input class="input" type="email" name="email" value="<%=email%>"
						placeholder="Enter Email (*)" required />
				</div>
				<div class="form-group">
					<input class="input" type="text" name="username"
						value="<%=username%>" placeholder="Enter Username (*)" required />
				</div>
				<div class="form-group">
					<input class="input" type="text" name="phone" value="<%=phone%>"
						placeholder="Enter Phone (*)" required/>
				</div>
				<div class="form-group">
					<input class="input" style="float:left;width: 100%"type="password" name="password"
						value="<%=password%>" placeholder="Enter Password (*)" required />
					<img src= "img/mostra_pass.png" id = "imgPassword" onmouseover="hoverDiv()" onmouseout="outDiv()" onclick="show()" style="margin-top:0.5%; cursor: pointer;height:25px;width: 25px; float: left;">
				</div>
				<div class="form-group" align="center" style="margin-top: 10%">
					<div class="g-recaptcha" data-sitekey="6LfkEM8UAAAAAI287NfDPLM59LsVCPWCyVY6uodZ" data-callback="showbutton"></div>
				</div>
				<button id="btn" style="margin-top: 10%" class="primary-btn" disabled>Join Us!</button>
				<a href="logInForm.jsp" style="margin-left: 1%">Already
					registered? Log in!</a>
				<div class="container-mf mt-3">(*) Required fields</div>
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
		document.signup.password.type = "text";
		document.getElementById('imgPassword').style.backgroundColor = "#c2c2c2";
		cont++;
	}
	else
	{
		document.signup.password.type = "password";
		document.getElementById('imgPassword').style.backgroundColor = "";
		cont++;
	}
}
</script>
</html>