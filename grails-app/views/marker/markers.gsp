<!DOCTYPE html>
<head>
    <style>
    #map{
        height:800px;
        width:100%;
    }
    </style>
</head>
<body>
    <div id="map"></div>
    <script>
        function initMap(){
            // Map options
            var bsas = {lat:-34.603722,lng:-58.381592};
            var options = {
                zoom: 10,
                center: bsas
            };

            // New map
            var map = new google.maps.Map(document.getElementById('map'), options);

            // Add markers
            <g:each in="${markerList}">
                props = {
                    title:'${it.title}',
                    latitude:${it.latitude},
                    longitude:${it.longitude},
                    visible:${it.visible}
                };

                <g:if test="${it.iconImage}">
                    props.iconImage = '${it.iconImage}';
                </g:if>

                <g:if test="${it.description}">
                    props.description = '${it.description}';
                </g:if>

                addMarker(props);
            </g:each>

            // Add Marker Function
            function addMarker(props){
                var marker = new google.maps.Marker({
                    position:{lat:props.latitude,lng:props.longitude},
                    map:map
                });

                // Check for customicon
                if(props.iconImage){
                    // Set icon image
                    marker.setIcon(props.iconImage);
                }

                // Check content
                var content = '<h4>' + props.title + '</h4>';
                if(props.description){
                    content = content + props.description;
                }

                var infoWindow = new google.maps.InfoWindow({
                    content:content
                });

                marker.addListener('click', function(){
                    infoWindow.open(map, marker);
                });

            }
        }
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDP827zHnIce50b1GTB8QPrHOUBFwcsGyw&callback=initMap">
    </script>
</body>
</html>
