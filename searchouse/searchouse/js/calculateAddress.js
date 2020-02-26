function calcAddress(district){
			
				var geocoder = new google.maps.Geocoder();
				var address = district;
			
				geocoder.geocode({'address' : address},
								function(results, status) {
									if (status == 'OK') {
										var locations = results[0].geometry.location;	
										document.getElementById("lan").value = results[0].geometry.location.lat();
										document.getElementById("lng").value = results[0].geometry.location.lng();
										
									} 
								});
			
}