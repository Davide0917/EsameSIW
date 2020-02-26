$(document).ready(function() {
    $("[name='update-billing-address']").click(function () {
    	var nome = $("[name='first-name']").val();
    	var cognome = $("[name='last-name']").val();
    	var email = $("[name='email']").val();
    	var indirizzo = $("[name='address']").val();
    	var citta = $("[name='city']").val();
    	var nazione = $("[name='country']").val();
    	var cap = $("[name='zip-code']").val();
    	var telefono = $("[name='tel']").val();
    	var IndirizzoFatturazione_Utente = $("[name='indirizzo-fatturazione-utente']").val();
    	
    	var indirizzo = {
    			nome: nome,
    			cognome: cognome,
    			email: email,
    			indirizzo: indirizzo,
    			citta: citta,
    			nazione: nazione,
    			cap: cap,
    			telefono: telefono,
    			IndirizzoFatturazione_Utente: IndirizzoFatturazione_Utente
    	};
    	
    	$.ajax({
            type: "POST",
            url: "user",
            dataType: 'json',
            data: {"indirizzo": JSON.stringify(indirizzo), "whatsend": "UpdateIndirizzo"},
            success: function(status) {
            	success_error("Entered", "Billing address updated!");
                console.log("Entered", status);
            },
            error: function(error) {
            	success_error("e", "Billing address not updated!");
            	console.log("Error", error);
            }
        });
    });
    
    $("[name='update-user']").click(function () {
    	var username = $("[name='username']").val();
    	var nome = $("[name='user-name']").val();
    	var cognome = $("[name='user-last-name']").val();
    	var email = $("[name='user-email']").val();
    	
    	var utente = {
    			username: username,
    			nome: nome,
    			cognome: cognome,
    			email: email
    	};
    	
    	$.ajax({
            type: "POST",
            url: "user",
            dataType: 'json',
            data: {"utente": JSON.stringify(utente), "whatsend": "UpdateUtente"},
            success: function(status) {
            	alert("Utente Updated")
                console.log("Entered", status);
            	 $("[name='span-username']").html(username);
            },
            error: function(error) {
            	alert("Utente Not Updated")
                console.log("Error", error);
            }
        });
    });
});