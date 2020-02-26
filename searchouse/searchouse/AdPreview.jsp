<%@page import="java.applet.AppletContext"%>
<%@page import="jdk.nashorn.api.scripting.JSObject"%>
<%@page import="java.applet.Applet"%>
<%@page import="searchouse.bean.SearchAdsBean"%>
<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@page import="searchouse.utils.AppUtil"%>
<%@page import="searchouse.bean.AdsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@page import="netscape.javascript.*" %>
<%@page import="java.applet.Applet" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	// NOTA BENE!!
	// CI CREIAMO UNA LISTA DEGLI ANNUNCI CHE VOGLIAMO VISUALIZZARE
	// E LA CARICHIAMO NELLA SESSION E QUI LA PRELEVIAMO PER VISUALIZZARLA
	// A SECONDO DEI FILTRI CHE DOVRANNO OPPRTUNAMENTE ESSERE GESTITI TRAMITE 
	// UN BEAN, VERRA GENERATA UNA LISTA DIVERSA MA CARICATA IN SESSION SEMPRE 
	// SOTTO LO STESSO NOME IN QUESTO CASO "ADSLIST"
	SearchAdsBean querys = new SearchAdsBean();

	if (request.getSession() != null && AppUtil.getData(session, "QUERY") != null) {
		querys = (SearchAdsBean) AppUtil.getData(session, "QUERY");
	
	}
	ArrayList<AdsBean> ads = new ArrayList<AdsBean>();
	if (request.getSession() != null && AppUtil.getData(session, "ADLIST") != null) {
		ads = AppUtil.getAllAds(session);
	}
	boolean img = false;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="js/calculateAddress.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <title></title> -->
</head>
<body>

	<script type="text/javascript">
		function OpenAD(index){
			var x = index.toString();
 			var path = "/searchouse/HomePage?AdIndex="+x+"&whatsend=openAd";
			document.location.href = path;
		}
	</script>
	
	<%
		if (!ads.isEmpty()) {
	%>

	<div class="container">
		<div class="summary-row left-side-listing">
			<h4><span class="count-ads hidden-xs"><%=ads.size() %>: case trovate </span></h4>
		</div>
		
		<%
				int index = 0;
				// QUESTO DOVREBBE ESSERE IL CODICE PER VISUALIZZARE I DATI DELL'ANTEPRIMA DELL'ANNUNCIO 
				// VA FORMATTATO OPPORTUNAMENTE CON I CSS
				for (AdsBean ad : ads) {
					String path;
					
					//	Stampiamo solo i primi 20 caratteri della descrizione
					String description = ad.getDescription().substring(0, 20)+"...";
					
					if (!ad.getImagesName().isEmpty())
						path = "./img/" + ad.getImagesName().get(0);
						else path = "./img/imgAssent.png";
		%>
		<ul class="annunci-list">
			<li class="listing-item listing-item--wide js-row-detail" onclick="OpenAD(<%=index%>)">
				<div class="listing-item_image">
					<div class="image-cutter">
						<img src="<%=path%>" width="150" height="150">
					</div>
				</div>
				<div class ="listing-item_body">
					<p class = "titolo text-primary"> Titolo: <%=ad.getTitle()%></p>
					<ul class = "listing-features list-piped">
						<li class ="lif__item lif__pricing">
							<span style="font-size: 16px;">a&nbsp;</span> € <%=ad.getPrice()%>
						</li>
						<li class = "lif__item">
							<div class="lif__data">
								<span class="text-bold"><%=ad.getDimension()%></span>&nbsp;m
								<sup>2</sup>
							</div>
							<div class="lif__text lif--muted">superficie</div>	
						</li>
						<li class="lif__item">
							<div class="lif__data">
								<%if(ad.getType().equals("typeVendita")){%>
									<span class="text-bold">Vendita</span>
								<%}else{%>
									<span class="text-bold">Affitto</span>
								<%} %>
							</div>
							<div class="lif__text lif--muted">Tipo di annuncio</div>	
						</li>
					</ul>
					<div class="descrizione">
						<div class ="descrizione__titolo">
							Provincia: <%=ad.getProvincia()%> Via: <%=ad.getAddress()%>
						</div>
						<div class="descrizione__truncate">
							<%=description %>
						</div>
					</div>
				</div>
			</li>
		<%
				index++;
				}
		%>
		</ul>
	</div>
	<%
		} else{
	%><div style="text-align: center; margin-top:2%; justify-content: center; align-items: center;width: 90%;">
		<div style="position: relative; width: 100%;height:250px;">
			<img src="./img/noAd.png" width="150" height="150"
				style="position: absolute; margin: auto; top: 0; left: 0; right: 0; bottom: 0;">
		<h5>Nessun annuncio presente...</h5>
		</div>
		<h5><a href="/searchouse/newAd">Inserisci un annuncio</a></h5>
	</div><%} %>
	<script type="text/javascript">	
				
	</script>
	<script async defer
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATChsEAfYANpVf1-7gRbEstc35ddZlGgw"></script >
	
</body>
</html>