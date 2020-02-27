<%@page import="java.util.ArrayList"%>
<%@page import="searchouse.bean.AdsBean"%>
<%@page import="searchouse.utils.AppUtil"%>
<%@page import="searchouse.bean.SearchAdsBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	SearchAdsBean query = new SearchAdsBean();
	ArrayList<AdsBean> allAds = new ArrayList<AdsBean>();
	boolean hasQuery = false;
	if (request.getSession() != null && AppUtil.getData(session, "QUERY") != null) {
		query = (SearchAdsBean) AppUtil.getData(session, "QUERY");
		hasQuery = true;
	}
	if (request.getSession() != null && AppUtil.getData(session, "ADLIST") != null) {
		allAds = AppUtil.getAllAds(session);
	}
	boolean openAddress = false;

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/calculateAddress.js"></script>
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
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body onload="initAutocomplete()">
	<div class="col-md-6 highlight"
		style="margin-top: 1%; margin-left: 1%; margin-right: 1%">
		<h4 class="text-uppercase" align="center" style="margin-top: 2%">Cerca
			annunci</h4>
		<form class="review-form" action="searchAds" method="post">
			<div class="form-group">
				<fieldset>
					<div class="custom-select" align="center">
						<select name="provincia" id ="provincia" style="width: 100%">
							<% if (hasQuery) {%>
								<%if(query.getProvincia().equals("Seleziona Provincia")){ %>
									<option value ="Seleziona Provincia"> Seleziona Provincia </option> 
										<option value="Agrigento">Agrigento</option>
										<option value="Alessandria">Alessandria</option>
										<option value="Ancona">Ancona</option>
										<option value="Aosta">Aosta</option>
										<option value="Arezzo">Arezzo</option>
										<option value="Ascoli Piceno">Ascoli Piceno</option>
										<option value="Asti">Asti</option>
										<option value="Avellino">Avellino</option>
										<option value="Bari">Bari</option>
										<option value="Barletta-Andria-Trani">Barletta-Andria-Trani</option>
										<option value="Belluno">Belluno</option>
										<option value="Benevento">Benevento</option>
										<option value="Bergamo">Bergamo</option>
										<option value="Biella">Biella</option>
										<option value="Bologna">Bologna</option>
										<option value="Bolzano">Bolzano</option>
										<option value="Brescia">Brescia</option>
										<option value="Brindisi">Brindisi</option>
										<option value="Cagliari">Cagliari</option>
										<option value="Caltanissetta">Caltanissetta</option>
										<option value="Campobasso">Campobasso</option>
										<option value="Carbonia-iglesias">Carbonia-iglesias</option>
										<option value="Caserta">Caserta</option>
										<option value="Catania">Catania</option>
										<option value="Catanzaro">Catanzaro</option>
										<option value="Chieti">Chieti</option>
										<option value="Como">Como</option>
										<option value="Cosenza">Cosenza</option>
										<option value="Cremona">Cremona</option>
										<option value="Crotone">Crotone</option>
										<option value="Cuneo">Cuneo</option>
										<option value="Enna">Enna</option>
										<option value="Fermo">Fermo</option>
										<option value="Ferrara">Ferrara</option>
										<option value="Firenza">Firenze</option>
										<option value="Foggia">Foggia</option>
										<option value="Forlì">Forl&igrave;-Cesena</option>
										<option value="Frosinone">Frosinone</option>
										<option value="Genova">Genova</option>
										<option value="Gorizia">Gorizia</option>
										<option value="Grosseto">Grosseto</option>
										<option value="Imperia">Imperia</option>
										<option value="Isernia">Isernia</option>
										<option value="La spezia">La spezia</option>
										<option value="L'Aquila">L'aquila</option>
										<option value="Latina">Latina</option>
										<option value="Lecce">Lecce</option>
										<option value="Lecco">Lecco</option>
										<option value="Livorno">Livorno</option>
										<option value="Lodi">Lodi</option>
										<option value="Lucca">Lucca</option>
										<option value="Macerata">Macerata</option>
										<option value="Mantova">Mantova</option>
										<option value="Massa-Carrara">Massa-Carrara</option>
										<option value="Matera">Matera</option>
										<option value="Medio Campidano">Medio Campidano</option>
										<option value="Messina">Messina</option>
										<option value="Milano">Milano</option>
										<option value="Modena">Modena</option>
										<option value="Monza e della Brianza">Monza e della
											Brianza</option>
										<option value="Napoli">Napoli</option>
										<option value="Novara">Novara</option>
										<option value="Nuoro">Nuoro</option>
										<option value="Ogliastra">Ogliastra</option>
										<option value="Olbia-Tempio">Olbia-Tempio</option>
										<option value="Oristano">Oristano</option>
										<option value="Padova">Padova</option>
										<option value="Palermo">Palermo</option>
										<option value="Parma">Parma</option>
										<option value="Pavia">Pavia</option>
										<option value="Perugia">Perugia</option>
										<option value="Pesaro e Urbino">Pesaro e Urbino</option>
										<option value="Pescara">Pescara</option>
										<option value="Piacenza">Piacenza</option>
										<option value="Pisa">Pisa</option>
										<option value="Pistoia">Pistoia</option>
										<option value="Pordenone">Pordenone</option>
										<option value="Potenza">Potenza</option>
										<option value="Prato">Prato</option>
										<option value="Ragusa">Ragusa</option>
										<option value="Ravenna">Ravenna</option>
										<option value="Reggio di Calabria">Reggio di Calabria</option>
										<option value="Reggio nell'Emilia">Reggio nell'Emilia</option>
										<option value="Rieti">Rieti</option>
										<option value="Rimini">Rimini</option>
										<option value="Roma">Roma</option>
										<option value="Rovigo">Rovigo</option>
										<option value="Salerno">Salerno</option>
										<option value="Sassari">Sassari</option>
										<option value="Savona">Savona</option>
										<option value="Siena">Siena</option>
										<option value="Siracusa">Siracusa</option>
										<option value="Sondrio">Sondrio</option>
										<option value="Taranto">Taranto</option>
										<option value="Teramo">Teramo</option>
										<option value="Terni">Terni</option>
										<option value="Torino">Torino</option>
										<option value="Trapani">Trapani</option>
										<option value="Trento">Trento</option>
										<option value="Treviso">Treviso</option>
										<option value="Trieste">Trieste</option>
										<option value="Udine">Udine</option>
										<option value="Varese">Varese</option>
										<option value="Venezia">Venezia</option>
										<option value="Verbano-Cusio-Ossola">Verbano-Cusio-Ossola</option>
										<option value="Vercelli">Vercelli</option>
										<option value="Verona">Verona</option>
										<option value="Vibo valentia">Vibo valentia</option>
										<option value="Vicenza">Vicenza</option>
										<option value="Viterbo">Viterbo</option>
							
								<%} else {%>
									
									<option value ="<%=query.getProvincia()%>"> <%=query.getProvincia()%> 
									</option> 	

								<% }%>
						
						
							<%}else{ %>
								<option value ="Seleziona Provincia"> Seleziona Provincia </option> 
								<option value="Agrigento">Agrigento</option>
								<option value="Alessandria">Alessandria</option>
								<option value="Ancona">Ancona</option>
								<option value="Aosta">Aosta</option>
								<option value="Arezzo">Arezzo</option>
								<option value="Ascoli Piceno">Ascoli Piceno</option>
								<option value="Asti">Asti</option>
								<option value="Avellino">Avellino</option>
								<option value="Bari">Bari</option>
								<option value="Barletta-Andria-Trani">Barletta-Andria-Trani</option>
								<option value="Belluno">Belluno</option>
								<option value="Benevento">Benevento</option>
								<option value="Bergamo">Bergamo</option>
								<option value="Biella">Biella</option>
								<option value="Bologna">Bologna</option>
								<option value="Bolzano">Bolzano</option>
								<option value="Brescia">Brescia</option>
								<option value="Brindisi">Brindisi</option>
								<option value="Cagliari">Cagliari</option>
								<option value="Caltanissetta">Caltanissetta</option>
								<option value="Campobasso">Campobasso</option>
								<option value="Carbonia-iglesias">Carbonia-iglesias</option>
								<option value="Caserta">Caserta</option>
								<option value="Catania">Catania</option>
								<option value="Catanzaro">Catanzaro</option>
								<option value="Chieti">Chieti</option>
								<option value="Como">Como</option>
								<option value="Cosenza">Cosenza</option>
								<option value="Cremona">Cremona</option>
								<option value="Crotone">Crotone</option>
								<option value="Cuneo">Cuneo</option>
								<option value="Enna">Enna</option>
								<option value="Fermo">Fermo</option>
								<option value="Ferrara">Ferrara</option>
								<option value="Firenza">Firenze</option>
								<option value="Foggia">Foggia</option>
								<option value="Forlì">Forl&igrave;-Cesena</option>
								<option value="Frosinone">Frosinone</option>
								<option value="Genova">Genova</option>
								<option value="Gorizia">Gorizia</option>
								<option value="Grosseto">Grosseto</option>
								<option value="Imperia">Imperia</option>
								<option value="Isernia">Isernia</option>
								<option value="La spezia">La spezia</option>
								<option value="L'Aquila">L'aquila</option>
								<option value="Latina">Latina</option>
								<option value="Lecce">Lecce</option>
								<option value="Lecco">Lecco</option>
								<option value="Livorno">Livorno</option>
								<option value="Lodi">Lodi</option>
								<option value="Lucca">Lucca</option>
								<option value="Macerata">Macerata</option>
								<option value="Mantova">Mantova</option>
								<option value="Massa-Carrara">Massa-Carrara</option>
								<option value="Matera">Matera</option>
								<option value="Medio Campidano">Medio Campidano</option>
								<option value="Messina">Messina</option>
								<option value="Milano">Milano</option>
								<option value="Modena">Modena</option>
								<option value="Monza e della Brianza">Monza e della
									Brianza</option>
								<option value="Napoli">Napoli</option>
								<option value="Novara">Novara</option>
								<option value="Nuoro">Nuoro</option>
								<option value="Ogliastra">Ogliastra</option>
								<option value="Olbia-Tempio">Olbia-Tempio</option>
								<option value="Oristano">Oristano</option>
								<option value="Padova">Padova</option>
								<option value="Palermo">Palermo</option>
								<option value="Parma">Parma</option>
								<option value="Pavia">Pavia</option>
								<option value="Perugia">Perugia</option>
								<option value="Pesaro e Urbino">Pesaro e Urbino</option>
								<option value="Pescara">Pescara</option>
								<option value="Piacenza">Piacenza</option>
								<option value="Pisa">Pisa</option>
								<option value="Pistoia">Pistoia</option>
								<option value="Pordenone">Pordenone</option>
								<option value="Potenza">Potenza</option>
								<option value="Prato">Prato</option>
								<option value="Ragusa">Ragusa</option>
								<option value="Ravenna">Ravenna</option>
								<option value="Reggio di Calabria">Reggio di Calabria</option>
								<option value="Reggio nell'Emilia">Reggio nell'Emilia</option>
								<option value="Rieti">Rieti</option>
								<option value="Rimini">Rimini</option>
								<option value="Roma">Roma</option>
								<option value="Rovigo">Rovigo</option>
								<option value="Salerno">Salerno</option>
								<option value="Sassari">Sassari</option>
								<option value="Savona">Savona</option>
								<option value="Siena">Siena</option>
								<option value="Siracusa">Siracusa</option>
								<option value="Sondrio">Sondrio</option>
								<option value="Taranto">Taranto</option>
								<option value="Teramo">Teramo</option>
								<option value="Terni">Terni</option>
								<option value="Torino">Torino</option>
								<option value="Trapani">Trapani</option>
								<option value="Trento">Trento</option>
								<option value="Treviso">Treviso</option>
								<option value="Trieste">Trieste</option>
								<option value="Udine">Udine</option>
								<option value="Varese">Varese</option>
								<option value="Venezia">Venezia</option>
								<option value="Verbano-Cusio-Ossola">Verbano-Cusio-Ossola</option>
								<option value="Vercelli">Vercelli</option>
								<option value="Verona">Verona</option>
								<option value="Vibo valentia">Vibo valentia</option>
								<option value="Vicenza">Vicenza</option>
								<option value="Viterbo">Viterbo</option>
						<%}%>
							
						</select>
					</div>
				</fieldset>
			
			</div>
			<div class="form-group">
				<fieldset>
					<h6 class="text-uppercase" align="center">Distanza</h6>
					<div class="custom-select" align="center">
						<select id="distance" onchange="changeValueDistance();" name="distance"  style="width: 100%"> 
							<% if (hasQuery) {%>
							
							<%if(query.getDistance().equals("Seleziona Distanza")){%>
										
							<option value="Seleziona Distanza">Seleziona Distanza
							</option>
							
							<option value="5">5 Km</option>
							<option value="10">10 Km</option>
							<option value="50">50 Km</option>
							<option value="100">100 Km</option>
							<%}else{ %>
								<option> <%=query.getDistance()%> <%if(query.getDistance().charAt(query.getDistance().length()-1) != 'm'){%> Km <%} %></option>
							<%} 
							} else {%>
								<option value="Seleziona Distanza">Seleziona Distanza
							</option>
							
							<option value="5">5 Km</option>
							<option value="10">10 Km</option>
							<option value="50">50 Km</option>
							<option value="100">100 Km</option>
							<%} %>
						</select>
						
					</div>
				</fieldset>
				
			</div>
		
			<div class="form-group"><h6 class="text-uppercase" align="center">Inserire Via</h6>
			<input <%if(hasQuery){ %>value="<%=query.getDistrict()%>"  <%} %>id="input"name="district" autocomplete="off" class="input" type="text" placeholder="Punta di calcolo" />
			<div id="change"> 
			</div>	
			</div>
			<div class="form-group">
				<h6 class="text-uppercase" align="center">Superficie</h6>
				<input class="input" onclick ="CallCalc()" type="number" min="10" max="1500" step="5"
					name="minSuperficie" placeholder="Min:" <%if (hasQuery){%>
					value="<%=query.getMinSuperficie()%><%}%>"> <input class="input"
					type="number" min="10" max="1500" step="5" name="maxSuperficie"
					placeholder="Max:" <%if (hasQuery){%>
					value="<%=query.getMaxSuperficie()%><%}%>">
			</div>
			<div class="form-group">
				<h6 class="text-uppercase" align="center">Prezzo</h6>
				<input class="input" type="number" min="1" max="15000000"
					name="minPrezzo" placeholder="Min: " <%if (hasQuery){%>
					value="<%=query.getMinPrezzo()%><%}%>"> 
					<input class="input"
					type="number" min="10" max="20000000" name="maxPrezzo"
					placeholder="Max: " <%if (hasQuery){%>
					value="<%=query.getMaxPrezzo()%><%}%>">
			</div>
			<div class="form-group" align="center" >
				<h6 class="text-uppercase" align="center">Tipo</h6>
				<%
					if (hasQuery) {
				%>
					<input name="type" <%if(query.getType().equals("typeAffitto")) {%> checked="checked" <%} %> value="typeAffitto" type="radio" >Affitto 
					<input name="type" <%if(query.getType().equals("typeVendita")) {%> checked="checked" <%} %> value="typeVendita" type="radio">Vendita
					<input name="type" <%if(query.getType().equals("NessunCampo")) {%> checked="checked" <%} %> value="NessunCampo" type= "radio"> Nessuno Campo 
				<%
				} else {
				%>
					<input name="type" value="typeAffitto" type="radio" >Affitto 
					<input name="type" value="typeVendita" type="radio">Vendita
					<input name="type" checked="checked" value="NessunCampo" type= "radio"> Nessuno Campo 
				<%} %>

			</div>
			<input type="hidden" id="lan" name ="lan">
			<input type="hidden" id="lng" name ="lng">
	
		
			<button onclick ="CallCalc();" class="primary-btn">Search</button>
	
			<a href="/searchouse/index">Elimina filtri</a>
		</form>
		
		
	</div>
	<script>
				var autocomplete;
				function initAutocomplete() {
					  // Create the autocomplete object, restricting the search predictions to
					  // geographical location types.
					var input = document.getElementById('input');
					var options = {
					 	componentRestrictions: {country: 'it'}
					};
			
					autocomplete = new google.maps.places.Autocomplete(input, options);
					autocomplete.setTypes("changetype-address");
					calcAddress(input);
					
				}
				function CallCalc(){
					var call = document.getElementById('input').value;
					calcAddress(call);
				}
				var r = false;
				function changeValueDistance(){
					
					if(document.getElementById("distance").innerHTML != 'Seleziona Distanza' && r == false){
						var button = document.createElement("input");
						 button.value = "Calcola Coordinate";
						 button.type = "button";
						 button.id = "CalcolaCoo";
						 button.attributes = "required";
						 document.getElementById("input").required = true;
						 button.required = true;
						 document.getElementById("change").appendChild(button);
						 r = true;
						 button.addEventListener ("click", function() {
							 alert('Coordinate Calcolate')
							  CallCalc();
						})
					}
				}
				
				
											
	</script>
		

	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATChsEAfYANpVf1-7gRbEstc35ddZlGgw&libraries=places&callback=initAutocomplete" async defer></script>
</body>
</html>