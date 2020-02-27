<%@page import="searchouse.bean.AdsBean"%>
<%@page import="searchouse.utils.AppUtil"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script type="text/javascript" src="js/api.js"></script>
<script type="text/javascript" src="js/calculateAddress.js"></script>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="./img/logo5.png" type="image/png" />
<title>Searchouse: New Ad</title>
</head>
<body>

	<%
		if (AppUtil.getLoginedUser(session) == null) {
			response.sendRedirect("/searchouse/index");
		}
	%>
	<%@ include file="header.jsp"%>
	<%
		response.setHeader("cache-control", "no-cache,no-store,must-revalidate");
	%>
	<div class="container">
		<h4 class="text-uppercase" align="center"
			style="margin-top: 2%; margin-bottom: 2%">Inserisci dati
			annuncio</h4>
		<form name="NewAd" action="/searchouse/newAd" method="post"
			autocomplete="off" enctype="multipart/form-data">
			<div class="form-group">
				<h6 class="text-uppercase" align="center">Titolo</h6>
				<input class="input" type="text" name="title"
					placeholder="Titolo (*)" required/>
			</div>

			<div class="form-group">
				<fieldset>
					<h6 class="text-uppercase" align="center">Seleziona Provincia</h6>
					<div class="custom-select" align="center">
						<select id="provincia" name="provincia" style="width: 100%; padding-left:15px;">
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
						</select>
					</div>
				</fieldset>
			</div>
			<div class="form-group">
				<h6 class="text-uppercase" align="center">Via/Contrada</h6>
				<input id="input" class="input"  autocomplete="off" type="text" name="address"
					placeholder="Inserire Via/Contrada (*)" required/>
			</div>
			<div class="form-group">
				<h6 class="text-uppercase" align="center">Descrizione</h6>
				<textarea class="input" style="overflow: auto;" maxlength="300" name="description"
					placeholder="Inserire descrizione (*) [massimo 1000 caratteri]" onkeyup="ContaCaratteri()" required ></textarea>
				Tot : <input type="text" style="border: 0" name="conta" value="0" readonly>
			</div>
			<div class="form-group">
				<h6 class="text-uppercase" align="center">Dimensione</h6>
				<input class="input" type="number" min="30" max="1500" name="dimension"
					placeholder="Inserire dimensione dell'appartamento (*)" required />
			</div>
			<div class="form-group">
				<h6 class="text-uppercase" align="center">Prezzo</h6>
				<input class="input" onchange= "CallCalc()" type="number" min="50" max="20000000" name="price"
					placeholder="Inserire prezzo per l'appartamento (*)" required />
			</div>
			<div class="form-group" align="center">
				<h6 class="text-uppercase" align="center">Categoria Annuncio</h6>
				<input name="type" value="typeAffitto" type="radio">Affitto
				<input name="type" value="typeVendita" type="radio"
					checked="checked">Vendita
			</div>
			<div>
				<input type="hidden" id="lan" name ="lan">
				<input type="hidden" id="lng" name ="lng">
			</div>
			<h6 class= "text-uppercase" align="center">Inserire File</h6>
			<div align="center">
				<input style ="width:25%; height:5%"type="file" name="file_uploaded" multiple/>
			<br>	
				<div class="form-group" align="center">
					<div class="g-recaptcha"  data-sitekey="6LfkEM8UAAAAAI287NfDPLM59LsVCPWCyVY6uodZ" data-callback="showbutton"></div>
				</div>
				<button id="btn" class="primary-btn" onclick ="CallCalc()" style="margin-top: 2%; width: 20%;" disabled>Crea</button>
			</div>
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
			calcAddress(input.value);
		}
		
		function ContaCaratteri()
		{
		    document.NewAd.conta.value = document.NewAd.description.value.length;
		    if(document.NewAd.conta.value >= 300){
		    	document.NewAd.conta.style.color = 'red';
		    	window.alert("Massimo 300 caratteri!");
		    }
		    else
		    	document.NewAd.conta.style.color = 'black';
		}
		function CallCalc(){
			var call = document.getElementById('input').value;
			calcAddress(call);
		}
		
	</script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATChsEAfYANpVf1-7gRbEstc35ddZlGgw&libraries=places&callback=initAutocomplete" async defer></script>

	<%@ include file="footer.jsp"%>
</body>
</html>