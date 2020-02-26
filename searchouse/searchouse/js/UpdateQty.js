$(document).ready(function() {
	update_qnt_span();
});

function update_qnt_span() {
	$.ajax({
		type: "GET",
		url: "checkout",
		dataType: "text",
		data: {"whatsend" : "GetQtyCart"},
		success: function(status, result, xhr) {
			console.log("Entered ")
			$("#qtySpan").html((xhr.responseText.trim().split('\n'))[0]);
		},
		error: function(error) {
			console.log("Error", error)
		}
	});
}