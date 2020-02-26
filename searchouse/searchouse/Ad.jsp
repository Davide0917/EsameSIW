<%@page import="java.util.ArrayList"%>
<%@page import="searchouse.bean.AdsBean"%>
<%@page import="searchouse.utils.AppUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	AdsBean currentAd = new AdsBean();
	if (request.getSession() != null && AppUtil.getData(session, "AD") != null) {
		currentAd = (AdsBean) AppUtil.getData(session, "AD");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ad.js"></script>

<link type="text/css" rel="stylesheet" href="css/customBootstrap.css" />

</head>
<body onload="codeAddress()">

	<%
		if (AppUtil.getData(session, "AD") == null) {
			response.sendRedirect("/searchouse/index");
		} else {
	%>
	<%@ include file="header.jsp"%>
	<button type="button" style="margin-left: 12%; margin-top: 2%"
		class="btn btn-outline-primary" onclick="back()">Torna alla
		ricerca</button>
	<div class="container" style="margin-top: 2%; margin-bottom: 3%">
		<div id="demo" style="float: left; width: 50%; height: 35%"
			class="carousel slide" data-ride="carousel" data-interval="false">

			<!-- Indicators -->
			<ul class="carousel-indicators">
				<%
					int i = 0;
						ArrayList<String> listPath = new ArrayList<String>();
						listPath = (ArrayList<String>) currentAd.getImagesName();
						for (String x : listPath) {
				%>
				<li data-target="#demo" data-slide-to="<%=i%>" <%if (i == 0) {%>
					class="active" <%}%>></li>
				<%
					i++;
						}
				%>
			</ul>

			<!-- The slideshow -->
			<div class="carousel-inner">
				<%
					int index = 0;
					String p = null;
					if (listPath.isEmpty()){
					p = "./img/imgAssent.png";
					%>
					<div class="carousel-item <%if(index==0){%>active<%}%>">
						<img width="100%" src="<%=p %>" class="mx-auto d-block">
					</div>
					<%
					}
						for (String x : listPath) {
							p = "./img/" + x;
				%>
				<div class="carousel-item <%if (index == 0) {%>active<%}%>">
					<img width="100%" src="<%=p%>" class="mx-auto d-block">
				</div>
				<%
					index++;
						}
				%>
			</div>

			<!-- Left and right controls -->
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>
		<div style="margin-left: 55%">
			<div class="container-md mt-3">
				<h1><%=currentAd.getTitle()%></h1>
			</div>
			<div class="container-md mt-3">Provincia</div>
			<div class="container-md mt-3 border" style="opacity: .7;"><%=currentAd.getProvincia()%></div>
			<div class="container-md mt-3">Indirizzo</div>
			<div class="container-md mt-3 border" style="opacity: .7;"><%=currentAd.getAddress()%></div>
			<div class="container-md mt-3">Superficie</div>
			<div class="container-md mt-3 border" style="opacity: .7;"><%=currentAd.getDimension() + " m²"%></div>
			<div class="container-md mt-3">Tipo</div>
			<div class="container-md mt-3 border" style="opacity: .7;">
				<%
					if (currentAd.getType().equals("typeAffitto")) {
				%>
				In affitto
				<%
					} else {
				%>
				In vendita<%
					}
				%>
			</div>
			<div class="container-md mt-3">Descrizione</div>
			<div class="container-md mt-3 border" style="opacity: .7;"><%=currentAd.getDescription()%></div>
			<div class="container-md mt-3">Prezzo</div>
			<div class="container-md mt-3 border" style="opacity: .7;"><%=currentAd.getPrice() + " EUR"%></div>
			<div class="container-mf mt-3">Località</div>
			<div class="container-mf mt-3 border" id= "address" style="opacity: .7;"><%=currentAd.getAddress()%>, <%=currentAd.getProvincia()%></div>
		</div>
		<div style="margin-left: 55%; margin-top: 5%">
			<script>
			

				// Initialize and add the map
				var geocoder;
				var map;
			
				
				function codeAddress() {
					geocoder = new google.maps.Geocoder();
					var latlng = new google.maps.LatLng(-34.397, 150.644);
					var mapOptions = {
						zoom : 17,
						center : latlng
					}
					map = new google.maps.Map(document.getElementById('map'),
							mapOptions);
					
				 	var address = document.getElementById('address').innerHTML;
					geocoder.geocode({'address' : address},
									function(results, status) {
										if (status == 'OK') {
											map.setCenter(results[0].geometry.location);
											var marker = new google.maps.Marker({
														map : map,
														position : results[0].geometry.location
													});
										} else {
											alert('Geocode was not successful for the following reason: '+ status);
										}
									});
				}
			</script>
			<script
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATChsEAfYANpVf1-7gRbEstc35ddZlGgw">
				
			</script>
				<div id="map" style="height: 300px; width: 500px"></div>
			
			</div>
		</div>
		

		<%
			}
		%>
		<%@ include file="footer.jsp"%>
	
</body>
</html>