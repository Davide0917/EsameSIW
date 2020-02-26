<%@page import="java.util.ArrayList"%>
<%@page import="searchouse.bean.AdsBean"%>
<%@page import="searchouse.bean.AccountBean"%>
<%@page import="searchouse.utils.AppUtil"%>
<%
	ArrayList<AdsBean> ads = new ArrayList<AdsBean>();
	if (AppUtil.getLoginedUser(session) != null) {
		AccountBean user = (AccountBean) AppUtil.getLoginedUser(session);
		ads = new ArrayList<AdsBean>();
		ads = user.getAds();

	}
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="it">
<html>
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
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link rel="icon" href="./img/logo5.png" type="image/png" />
<title>Searchouse: MyAds</title>
</head>
<body>

	<script type="text/javascript">
		function OpenAD(index){
			var x = index.toString();
 			var path = "/searchouse/myAds?AdIndex="+x+"&whatsend=openAd";
			document.location.href = path;
		}
	</script>
	
	<%
		if (AppUtil.getLoginedUser(session) == null) {
			response.sendRedirect("/searchouse/index");
		}
	%>
	<%@ include file="header.jsp"%>

	<%
		response.setHeader("cache-control", "no-cache,no-store,must-revalidate");
		if (!ads.isEmpty()) {
	%>

	<div class="container">
		<div class="summary-row left-side-listing" style="margin-top: 2%">
			<h4>
				<span class="count-ads hidden-xs">Hai inserito: <%=ads.size()%></span>
			</h4>
		</div>
		<ul class="annunci-list">
			<%
				int index = 0;
				// QUESTO DOVREBBE ESSERE IL CODICE PER VISUALIZZARE I DATI DELL'ANTEPRIMA DELL'ANNUNCIO 
					// VA FORMATTATO OPPORTUNAMENTE CON I CSS
					for (AdsBean ad : ads) {
						String path;
						if (!ad.getImagesName().isEmpty())
							path = "./img/" + ad.getImagesName().get(0);
						else
							path = "./img/imgAssent.png";
			%>
			<li class="listing-item listing-item--wide js-row-detail" onclick="OpenAD(<%=index%>)">
				<div class="listing-item_image">
					<div class="image-cutter">
						<img src="<%=path%>" width="150" height="150">
					</div>
				</div>
				<div class="listing-item_body" style="float:left;">
					<p class="titolo text-primary">
						Titolo:
						<%=ad.getTitle()%></p>
					<ul class="listing-features list-piped">
						<li class="lif__item lif__pricing"><span
							style="font-size: 16px;">a&nbsp;</span> € <%=ad.getPrice()%></li>
						<li class="lif__item">
							<div class="lif__data">
								<span class="text-bold"><%=ad.getDimension()%></span>&nbsp;m <sup>2</sup>
							</div>
							<div class="lif__text lif--muted">superficie</div>
						</li>
						<li class="lif__item">
							<div class="lif__data">
								<%
									if (ad.getType().equals("typeVendita")) {
								%>
								<span class="text-bold">Vendita</span>
								<%
									} else {
								%>
								<span class="text-bold">Affitto</span>
								<%
									}
								%>
							</div>
							<div class="lif__text lif--muted">Tipo di annuncio</div>
						</li>
					</ul>
					<div class="descrizione">
						<div class="descrizione__titolo">
							Via:
							<%=ad.getAddress()%>
						</div>
						<div class="descrizione__truncate">
							<%=ad.getDescription()%>
						</div>
					</div>
				</div>
				<div style="float: right; margin-right: 3%; margin-top:2%; width: 6%; height: 30%">
					<form class="review-form" action="myAds" method="post">
						<button type="submit" name="Delete" value="<%=ad.getId()%>">
							<img src="./img/Delete.png" alt="" width="30px" height="30px">
						</button>
					</form>
				</div>

			</li>
			<%
				index++;
				}
			%>
		</ul>
	</div>
	<%
		} else {
	%><div
		style="text-align: center; margin-top: 2%; justify-content: center; align-items: center; width: 90%;">
		<div style="position: relative; width: 100%; height: 250px;">
			<img src="./img/noAd.png" width="150" height="150"
				style="position: absolute; margin: auto; top: 0; left: 0; right: 0; bottom: 0;">
			<h5>Nessun annuncio presente...</h5>
		</div>
		<h5>
			<a href="/searchouse/newAd">Inserisci un annuncio</a>
		</h5>
	</div>
	<%
		}
	%>
	<%@ include file="footer.jsp"%>
</body>
</html>