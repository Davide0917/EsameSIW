var formData = new FormData();
var file_to_upload_info = [];
var categories = [];

$(document).ready(function() {	
	get_categories();
	
	$('#upload-btn').hide();
	$('#upload-table').hide();
	
	$("#upload-btn").click(function() {
		var output = new FormData();
		var upload_data = false;
		
		for(const key of formData.keys()) {
			var row_id = "row-" + key;
			var input_name = $('#' + row_id).find('td:eq(1)').find('input');
			var input_genre = $('#' + row_id).find('td:eq(2)').find('select');
			var input_description = $('#' + row_id).find('td:eq(3)').find('textarea');
			var input_price = $('#' + row_id).find('td:eq(4)').find('input');
			
			if(input_name.val() !== "" && !is_duplicate(input_name.val())) {
				var description = input_description.val();
				if (description == "" || description == undefined) description = "Description is missing";
				var price = input_price.val();
				if (price == "" || price == undefined) price = "0";
				
				file_to_upload_info.push({
					titolo: input_name.val(), 
					brano_categoria: input_genre.val(), 
					descrizione: description, 
					prezzo: price,
					path: ""
				})
				
				document.getElementById("choose-btn").value = "";
				output.append(key, formData.get(key));
				$('#' + row_id).remove();
				upload_data = true;
			}
			
			if (input_name.val() === "") {
				input_name.css({"color":"red","border":"1px solid red"});
			}
			
			if (is_duplicate(input_name.val())) {
				success_error("e", "Il titolo "+ input_name.val() + " è già presente!");
				input_name.css({"color":"red","border":"1px solid red"});
			}
		}
		
		for (const key of output.keys()) formData.delete(key);
		if ($('#upload-table tr').length <= 1) { 
			$('#upload-btn').hide();
			$('#upload-table').hide();
		}
		
		if (upload_data) {
			var headers = "";
			
			$.ajax({
				type: "POST",
				url: "brano",
				enctype: "multipart/form-data",
				processData: false,
				contentType: false,
				cache: false,
				timeout: 600000,
				async: false,
				data: output,
				success: function(result, status, xhr) {
					console.log("File Inserted");
					headers = xhr.responseText; 
				},
				error: function(error) {
					success_error("e", "Files not inserted! Have you inserted billing address ?");
					console.log("Error", error);
				}
			});
			
			var arr = headers.trim().split("\n");
			for (var j = 0; j < arr.length; j++)
				file_to_upload_info[j].path = arr[j];
			
			$.ajax({
				type: "POST",
				url: "user",
				dataType: 'json',
				async: false,
				data: {"file_to_upload_info": JSON.stringify(file_to_upload_info), "whatsend": "InsertFileToUpload"},
				success: function(status) {
					success_error("Success!","Song Inserted!");
					console.log("Entered", status);
					get_brani_utente();
				},
				error: function(error) {
					success_error("e","Song not inserted! Have you inserted billing address ?");
					console.log("Error", error);
				}
			});
			
			file_to_upload_info = [];
		}
	});
});

function get_categories() {
	$.get("categorie", function(result, status, xhr) {
		categories = JSON.parse(xhr.responseText);
	}, "json");
}

function show_table() {
	var file_selector = document.getElementById("choose-btn");
	for (var i = 0; i < file_selector.files.length; i++) {
		if(formData.get((file_selector.files.item(i).name.split(".")[0]).toLowerCase().replace(/\s/g, '')) == undefined && ext_allowed(file_selector.files.item(i).name.split(".").pop())) {
			var row = "<tr id='row-" + (file_selector.files.item(i).name.split(".")[0]).toLowerCase().replace(/\s/g, '') + "'>";
			row +=  "<td>" + file_selector.files.item(i).name + "</td>";
			row += "<td><input class='form-control' type='text' placeholder='Insert title here*' maxlength='45' required></td>"
				
			var selectors = "";
			for (var j = 0; j < categories.length; j++)
				if(categories[j].nome == "Other") selectors += "<option selected='selected' value='" + categories[j].idCategoria + "'>" + categories[j].nome + "</option>";
				else selectors += "<option value='" + categories[j].idCategoria + "'>" + categories[j].nome + "</option>";
			row += "<td><select class='form-control'>" + selectors + "</select></td>";
			
			row += "<td><textarea class='form-control' maxlength='256' placeholder='&quot;Description missing&quot; by default'></textarea></td>";
			row += "<td><input class='form-control' type='number' min='0' max='999' placeholder='Free by default' step='any'></td>";
			row += "</tr>";
			
			$('#upload-btn').show();
			$('#upload-table').show();
			$('#upload-table').append(row);
			formData.append((file_selector.files.item(i).name.split(".")[0]).toLowerCase().replace(/\s/g, ''), file_selector.files.item(i))
		}
		
		if (!ext_allowed(file_selector.files.item(i).name.split(".").pop())) {
			success_error("e", "L'estensione ." + file_selector.files.item(i).name.split(".").pop() + " non è valida!")
		}
	}
}

function ext_allowed(ext) {
	if (ext === "mp3" || ext === "ogg")
		return true;
	
	return false;
}

function is_duplicate(titolo) {
	for(var i = 0; i < brani_utente.length; i++)
		if (brani_utente[i].titolo == titolo)
			return true;
	
	return false;
}