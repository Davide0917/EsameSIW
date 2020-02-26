function get_commenti(){
	$.ajax({
			url: "commenti",
			type: "GET",
			dataType: 'json',
			data: {"whatsend": "commento"},
			success: function(result,status,xhr) {
	           console.log("Entered", status);
	           updateCommento(JSON.parse(xhr.responseText));
	       },
	       error: function(error) {
	       	console.log("Error", error);
	       }
		});
}

$(document).ready(function() {
  	 get_commenti();
  	
      $("#add-comment").click(function () {
    	  
      	var commento= $("#message").val();
      	var rating = $("input[name='rating']:checked").val();
      	
      	var valutazione = {
      			rating : rating ,
      			commento : commento ,
      			Brano_idBrano : 0,
      			Utente_nomeUtente : ""
      	};
      	
      	$.ajax({
              type: "POST",
              url: "rating",
              dataType: 'json',
              data: {"valutazione": JSON.stringify(valutazione), "whatsend": "valuta"},
              success: function(status) {
            	success_error("Success!", "Comment inserted");
              	console.log("Entered: ", status);
              	get_commenti();
              },
              error: function(error) {
              	success_error("e", "Comment not inserted");
              	console.log("Error: ", error);
              	
              }
          });
      	
      	document.getElementById("message").value="";
      });
  });

  function updateCommento(valutazioni){
	
	var newComment="";
  	var comment_section = $("#comment-section");
  	
  	if(valutazioni.length == 0) {
  		console.log("empty")
  		newComment = "<div><h4>No comment found!</h4></div>";
  	} else {
	  	for(var i = 0; i < valutazioni.length; i++) {
		  	newComment += "<div class='single-review'>" +
		  					"<div class='review-heading'>" +
		  						"<div><a><i class='fa fa-user-o'> " + valutazioni[i].Utente_nomeUtente  + "</i></a></div>" +
		  						"<div><a href='#'><i class='fa fa-clock-o'></i>" + valutazioni[i].date_ins + "</a></div>" +
		  					"<div class='review-rating pull-right'>";
		  					for(var j = 0; j < valutazioni[i].rating; j++)
		  						newComment += "<i class='fa fa-star'></i>";
		  					for(var j = 0; j< 5-valutazioni[i].rating; j++)
		  						newComment += "<i class='fa fa-star-o empty'></i>"
		  					newComment += "</div></div>";
		  					newComment += "<div class='review-body'>" +
		  									"<p>" + valutazioni[i].commento + "</p></div></div></div>";
	  	}
  	}
  	
  	
  	comment_section.html(newComment);
  	
    var votoComplessivo = 0;
    for (var i = 0; i < valutazioni.length; i++) {
  	  votoComplessivo+=valutazioni[i].rating;
    }
    var stella = 0;
    if (valutazioni.length != 0) {
    	stella = votoComplessivo / valutazioni.length;	
    }
    var star="";
    
    for(var i = 0;i < stella; i++){
  	  star+="<i class='fa fa-star'></i>";
    }
    for(var i = 0; i < 5-stella;i++){
  	  star+="<i class='fa fa-star-o empty'></i>";
    }
    
    document.getElementById("starsofsong").innerHTML=star;
 }