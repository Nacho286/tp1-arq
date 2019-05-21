<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>My Google Map</title>
    <style>
    #map{
        height:800px;
        width:100%;
    }
    </style>
</head>
<body>
<h1>My Google Map</h1>
<div id="map"></div>
<script>
    function initMap(){
        // Map options
        var bsas = {lat:-34.603722,lng:-58.381592};
        var options = {
            zoom: 8,
            center: bsas
        };

        // New map
        var map = new google.maps.Map(document.getElementById('map'), options);

        // Listen for click on map
        google.maps.event.addListener(map, 'click', function(event){
            // Add marker
            addMarker({coords:event.latLng});
        });

        // Array of markers
        var markers = [
            {
                coords:{lat:-34.603722,lng:-58.381592},
                iconImage:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
                content:'<h1>Lynn MA</h1>'
            },
            {
                coords:{lat:-33,lng:-58.381592},
                content:'<h1>Amesbury MA</h1>'
            },
            {
                coords:{lat:-30,lng:-68}
            }
        ];

        // Loop through markers
        for(var i = 0;i < markers.length;i++){
            // Add marker
            addMarker(markers[i]);
        }

        // Add Marker Function
        function addMarker(props){
            var marker = new google.maps.Marker({
                position:props.coords,
                map:map,
                //icon:props.iconImage
            });

            // Check for customicon
            if(props.iconImage){
                // Set icon image
                marker.setIcon(props.iconImage);
            }

            // Check content
            if(props.content){
                var infoWindow = new google.maps.InfoWindow({
                    content:props.content
                });

                marker.addListener('click', function(){
                    infoWindow.open(map, marker);
                });
            }
        }
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDP827zHnIce50b1GTB8QPrHOUBFwcsGyw&callback=initMap">
</script>
</body>
</html>
