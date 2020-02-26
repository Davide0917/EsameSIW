function success_error(status,msg) {
	var colore = "green";
	if(status === "e") {
		colore = "red";
		status = "Error!";
	}
		
	document.getElementById("error_success_status").innerHTML = status;
	document.getElementById('error_success_header').style.backgroundColor = colore;
	document.getElementById("error_success_msg").innerHTML = msg;
	$('#error_success').modal('show');
}