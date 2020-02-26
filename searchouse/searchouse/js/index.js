$(document).ready(function() {
	
	var categories = [];

	$.get("categorie", function(result, status, xhr) {
		categories = JSON.parse(xhr.responseText);
		for(var i = 0; i < categories.length; i++)
			$("#category-list").append("<li><a onclick='updateCatalogoByCategory(" + categories[i].idCategoria + ")'>" + categories[i].nome + "</a></li>");
	}, "json");
	
	var artists = [];
	$.get("artist", function(result, status, xhr) {
		artists = JSON.parse(xhr.responseText);
		for(var i = 0; i < artists.length; i++)
			$("#artist-list").append("<li><a onclick='updateCatalogoByArtist(" + artists[i].idUser + ")'>" + artists[i].username + "</a></li>")
	}, "json");
	
	$("#search-bar-brano").keyup(function() {
		var query = $("#search-bar-brano").val();
		$.ajax({
			url: "index",
			type: "GET",
			dataType: "json",
			data: {"whatsend": "GetBraniByQuery", "query": query},
			success: function(result, status, xhr) {
				console.log("Entered: ", status);
				updateCatalogo(JSON.parse(xhr.responseText));
			},
			error: function(error) {
				console.log("Error: ", error);
			}
		});
	});
	
	var min = 0;
	var max = 999;
	var slider = document.getElementById('price-slider');
	slider.noUiSlider.on('change', function (values, handle) {
		if (handle) {
			max = (values[handle]).slice(0, -1);
		} else {
			min = (values[handle]).slice(0, -1);;
		}
		console.log(min + " " + max); 
		
		
		$.ajax({
			url: "index",
			type: "GET",
			dataType: "json",
			data: {"whatsend": "GetBraniByPrice", "min": min, "max": max},
			success: function(result, status, xhr) {
				console.log("Entered: ", status);
				updateCatalogo(JSON.parse(xhr.responseText));
			},
			error: function(error) {
				console.log("Error: ", error);
			}
		});
	});
});

function updateCatalogoByArtist(idArtist){
	$.ajax({
		url: "index",
		type: "GET",
		dataType: "json",
		data: {"whatsend": "GetBraniByArtist", "idUtente": idArtist},
		success: function(result, status, xhr) {
			console.log("Entered: ", status);
			updateCatalogo(JSON.parse(xhr.responseText));
		},
		error: function(error) {
			console.log("Error: ", error);
		}
	});
}


function updateCatalogoByCategory(idCategoria) {
	$.ajax({
		url: "index",
		type: "GET",
		dataType: "json",
		data: {"whatsend": "GetBraniByCategoria", "idCategoria": idCategoria},
		success: function(result, status, xhr) {
			console.log("Entered: ", status);
			updateCatalogo(JSON.parse(xhr.responseText));
			
		},
		error: function(error) {
			console.log("Error: ", error);
		}
	});
}

function updateCatalogo(brani) {
	var newProduct = "";
	if(brani.length <= 0) {
		newProduct += "<div class='col-md-9 col-sm-6 col-xs-6'><h4>No result found!</h4></div>"
	}
	
	$('#row-catalogo').html("");
	for(var i = 0; i < brani.length; i++) {
		if(brani[i].disponibile){
			newProduct += "<div class='col-md-4 col-sm-6 col-xs-6'>" +
			 					"<div class='product product-single'>" +
			 						"<a href='brano?whatsend=GetDettaglioBrano&idBrano=" + brani[i].idBrano + "' >" +
			 							"<div class='product-thumb'>" +
			 								"<img src='./img/disk.png' alt=''>" +
			 							"</div>" +
			 						"</a>" + 
			 						"<div class='product-body'>" +
			 							"<h3 class='product-price'>" + brani[i].prezzo + " €</h3>" +
			 							"<div class='product-rating'>"; 
			 								for(var j = 0; j < brani[i].stelle; j++) 
											newProduct += "<i class='fa fa-star'></i>";
											for(var j = 0;j < 5-brani[i].stelle; j++) 
											newProduct += "<i class='fa fa-star-o empty'></i>";
			 								
			 							newProduct += "</div>" +  
			 							"<h2 class='product-name'><a href='brano?whatsend=GetDettaglioBrano&idBrano=" + brani[i].idBrano + "'>" + brani[i].titolo + "</a></h2>" +
			 							"<div class='product-btns'>";
			 							if(brani[i].prezzo <= 0)
			 								newProduct += "<a href='SONG/" + brani[i].path + "' class='primary-btn add-to-cart' download>" +
											 "<i class='fa fa-arrow-down'></i> Download</a>";
			 							else
			 								newProduct += "<button class='primary-btn add-to-cart' onclick='addToCart(" + brani[i].idBrano + ")'><i class='fa fa-shopping-cart'></i> Add to Cart</button>";
			 								
			 								
			 								var titolo_share = brani[i].titolo.replace(" ", "&#32");
			 								newProduct += "<button class='main-btn icon-btn' style='margin-left:1.5%' onclick=share('" + titolo_share + "','" + brani[i].idBrano + "')><i class='fa fa-share-alt'></i></button>" +  
			 							"</div>" + 
			 						"</div>" + 
			 					"</div>" + 
			 				"</div>";
		}
	}
	
	
	
	$('#row-catalogo').html(newProduct);
}