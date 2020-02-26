<%@page import="searchouse.bean.ErrorBean"%>
<%@page import="searchouse.bean.AccountBean"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String name = "";
	String surname_profile = "";
	String username_profile = "";
	String email = "";
	String phone = "";
	String password ="";
	String Print_password ="";
	boolean change = false; // premendo il tasto di modifica la pagina varia e vengono cambiate in box per variare i parametri che sono consentiti 

	AccountBean account_profile = new AccountBean();
	
	ErrorBean err = null;
	if(AppUtil.getData(session, "ERROR")!=null){
		err = (ErrorBean) AppUtil.getData(session, "ERROR");
	}

	if (request.getSession() != null && request.getSession().getAttribute("LOGINED_USER") != null) {
		account_profile = AppUtil.getLoginedUser(session);
		name = account_profile.getName() + "";
		surname_profile = account_profile.getSurname() + "";
		username_profile = account_profile.getUsername() + "";
		email = account_profile.getEmail() + "";
		phone = account_profile.getPhone() + "";
		password = account_profile.getPassword()+ "";
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/myprofile.css" />
<title>Searchouse: My Profile</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<!--	Questa riga di codice serve per non memorizzare le pagine nella cache e quindi non visualizzabili dopo 
			il logout
	 -->
	<%
		response.setHeader("cache-control", "no-cache,no-store,must-revalidate");
	%>
	<br>
	<%
		// Controlliamo che l'utente sia loggato altrimenti reinderizziamo alla home
		if (request.getSession().getAttribute("LOGINED_USER") == null) {
			response.sendRedirect("/searchouse/index");
		}
	%>
	
	<div class="container">
    <div class="row">
        <div class="col-md-3" style="float:left; width: 30%">
            <ul class="nav nav-pills nav-stacked admin-menu">
                <li class="active"><a href="#" data-target-id="dati"><i class="fa fa-fw"></i>I miei dati</a></li>
                <li><a href="#" data-target-id="password"><i class="fa fa-fw"></i>Cambia Password</a></li>
                <li><a href="#" data-target-id="delete"><i class="fa fa-fw"></i>Elimina Account</a></li>
            </ul>
        </div>
        <div class="col-md-9 well admin-content" style="float: left; width:65%;height:  300px; margin-right: 5%" id="dati">
			<h4 class="text-uppercase" >I miei dati</h4>
				<form name="UpdateProfile" action="/searchouse/profile" method="post">
					<div class="descrizione">
							<div>		
								<h5 class="text-uppercase" >Nome: <%=name%></h5> 
							</div>
							<div>
								<h5 class="text-uppercase" >Cognome: <%=surname_profile%></h5> 
							</div>
							<div>
								<h5 class="text-uppercase" >Username: <%=username_profile%></h5> 
							</div>
							<div>
								<h5 class="text-uppercase" >Password: ••••••••</h5> 
							</div>
							<div>
								<h5>Email: <input name="email"class="text-uppercase" style="width: 100%" value ="<%=email%>"></h5> 
							</div>
							<div>
								<h5>Telefono: <input name="phone"class="text-uppercase" style="width: 100%" value ="<%= phone%>"></h5> 
							</div>
					</div>
					<div>
						<input name="whatsend" type="hidden" value="changeData">
						<input type="submit" value="Modifica Dati">
					</div>
				</form>
        </div>
        <div class="col-md-9 well admin-content" style="float: left; width:65%;height:  300px; margin-right: 5%" id="password">
				<h4 class="text-uppercase">Modifica password</h4>
		
			<form name="ChangePass" action="/searchouse/profile" method="post">
				<div class="descrizione">
					<h5>Vecchia password: <input name="password" type="password" maxlength="45" style="width: 100%" value=""></h5>	
					<h5>Nuova password: <input name="newpassword" type="password" maxlength="45" style="width: 100%" value=""></h5>
				</div>
				<div style="float: left">
					<input name="whatsend" type="hidden" value="changePssw">
					<input type="submit" value="Cambia Password">
				</div>
					<img src= "img/mostra_pass.png" id = "imgPassword" onmouseover="hoverDiv()" onmouseout="outDiv()" onclick="show()" style="margin-top:-0.3%; cursor: pointer; margin-left:2%;height:30px;width: 30px; float: left;">
			</form>
        </div>
        <div class="col-md-9 well admin-content" style="float: left; width:65%;height:  300px; margin-right: 5%" id="delete">
            <h5>Confermando l'eliminazione dell'account tutti i tuoi dati e gli annunci inseriti verranno eliminati.</h5>
            <input class= "primary-btn" style="width: 50%; margin-right: 25%; margin-left: 25%;margin-top: 5%" type="button" value="Elimina account" onclick="deleteAccount()">
        </div>
    </div>
    
</div>
	
</body>

<%@ include file="footer.jsp"%>

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
				document.ChangePass.password.type = "text";
				document.ChangePass.newpassword.type = "text";
				document.getElementById('imgPassword').style.backgroundColor = "#c2c2c2";
				cont++;
			}
			else
			{
				document.ChangePass.password.type = "password";
				document.ChangePass.newpassword.type = "password";
				document.getElementById('imgPassword').style.backgroundColor = "";
				cont++;
			}
		}
		
		$(document).ready(function()
			{
			    var navItems = $('.admin-menu li > a');
			    var navListItems = $('.admin-menu li');
			    var allWells = $('.admin-content');
			    var allWellsExceptFirst = $('.admin-content:not(:first)');
			    
			    allWellsExceptFirst.hide();
			    navItems.click(function(e)
			    {
			        e.preventDefault();
			        navListItems.removeClass('active');
			        $(this).closest('li').addClass('active');
			        
			        allWells.hide();
			        var target = $(this).attr('data-target-id');
			        $('#' + target).show();
			    });
			});
		
		function deleteAccount() {
			if (window.confirm("Confermi l'eliminazione?")) {
				var path = "/searchouse/HomePage?whatsend=deleteaccount";
				document.location.href = path;
			}
		}
</script>
</html>