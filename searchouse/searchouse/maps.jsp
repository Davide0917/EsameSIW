<%@page import="searchouse.db.AdsDAO"%>
<%@page import="searchouse.bean.AdsBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	AdsDAO dao = new AdsDAO();
	ArrayList<AdsBean> ads = new ArrayList<AdsBean>();
	ads = dao.getAll();

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

<title>ViewMaps</title>
</head>
<body >
	<script type="text/javascript">
		function OpenAD(index){
			var x = index.toString();
 			var path = "/searchouse/HomePage?AdIndex="+x+"&whatsend=openAd";
			document.location.href = path;
		}
	</script>
	<%@ include file="header.jsp"%>
				
		<script>
			function markerAd(){
				var geocoder = new google.maps.Geocoder();	
				var map = new google.maps.Map(document.getElementById('map'), {
			        zoom: 7,
			        center: new google.maps.LatLng(42.50, 12.50),
			        mapTypeId: google.maps.MapTypeId.ROADMAP
			      });
				<%int index = 0;
				for(AdsBean ad: ads){%>
					var address = "<%=ad.getAddress()%>,<%=ad.getProvincia()%>";
					geocoder.geocode({'address' : address},
							function(results, status) {
								if (status == 'OK') {
								 	var locations = [
										['<span style="font-weight: bold"; onclick="OpenAD(<%=index%>)" ><%=ad.getTitle()%></span> ,<%=ad.getAddress()%>,  <%=ad.getProvincia()%>, Dimensioni: <%=ad.getDimension()%>, Prezzo: <%=ad.getPrice()%>',  results[0].geometry.location.lat(), results[0].geometry.location.lng()],		
										
									];
								
								var infowindow = new google.maps.InfoWindow();
								   
							    var marker, i;
							  
							      var iconBase = 'http://maps.google.com/mapfiles/ms/micons/';
							          var icons = [iconBase + 'red-dot.png',
							                      iconBase + 'purple-pushpin.png',
							                      iconBase + 'purple-pushpin.png'];
							  
							  
							      for (i = 0; i < locations.length; i++) {  
							        marker = new google.maps.Marker({
							          position: new google.maps.LatLng(locations[i][1], locations[i][2]),
							          map: map,
							          <%if(ad.getType().equals("typeAffitto")){%>
							          icon: icons[0]
							          <%} else {%>
							          icon: icons[1]
							          <%}%>
							        });
							  
							        google.maps.event.addListener(marker, 'click', (function(marker, i) {
							          return function() {
							            infowindow.setContent(locations[i][0]);
							            infowindow.open(map, marker);
							          }
							        })(marker, i));
							      }
								} else {
									alert('Geocode was not successful for the following reason: '+ status);
								}
							});
					
				<%index++; }%>
			  }
				//var marker = new google.maps.Marker({position: uluru, map: map});
			
					
		</script> 
			
		<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATChsEAfYANpVf1-7gRbEstc35ddZlGgw&callback=markerAd"></script >
	
	<div style="float:left; margin-left:2%; margin-top:2%">  
	
        <div class="container-md mt-2" style="opacity: .7;">Legenda</div>
       	<div class="container-md mt-2 border" style="opacity: .7;">Case In Affitto<img src="img/red-dot.png" alt=""></div>
       	<div class="container-md mt-2 border" style="opacity: .7;">Case In Vendita<img src="img/purple-pushpin.png" alt=""></div>
    </div>
	<div id="map" style="height: 550px; width: 1200px; margin: 0 auto; margin-top: 2%; margin-bottom:3%"></div>
	
	<%@ include file="footer.jsp"%>

</body>
</html>