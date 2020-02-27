<%@page import="searchouse.bean.ErrorBean"%>
<%@page import="searchouse.bean.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	ErrorBean err = null;
	if(AppUtil.getData(session, "ERROR")!=null){
		err = (ErrorBean) AppUtil.getData(session, "ERROR");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="it">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Hind:400,700"
	rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />
<link rel="stylesheet" href="css/font-awesome.min.css">
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>

<link type="text/css" rel="stylesheet" href="css/style.css" />
<link rel="icon" href="./img/logo5.png" type="image/png" />
<title>Searchouse: Home Page</title>
</head>
<body>

	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="row">
			<div>
				<%@ include file="searchAds.jsp" %>
			</div> 
			<div style="float:left; display:block; margin-top: 3%; margin-left: 0%; margin-bottom: 3%;width:65%;">
				<%@ include file="AdPreview.jsp" %>
			</div>
		</div>
	</div>
	<div style="clear:both;"></div>
	<%@ include file="footer.jsp"%>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
</body>
<%if(err!=null){ 
	AppUtil.removeData(session, "ERROR");%>
	<script type="text/javascript">
	setTimeout(function(){ alert("<%=err.getMessage()%>"); }, 100);
	</script>
<%} %>
</html>