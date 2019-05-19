<!DOCTYPE html>
<head>
    <meta name="layout" content="map">
</head>
<body>
    <div id="sidebar" class="sidebar">
        <h2>Menu</h2>
        %{-- <ul>
            <g:each in="${markerList}">
                <g:if test="${it.visible}">
                    <li><a href=""><h3>${it.title}</h3>${it.description}</a></li>
                </g:if>
            </g:each>
        </ul> --}%
    </div>
    <div id="map"></div>
    <script>
        function initMap(){
            // Map options
            var bsas = {lat:-34.603722,lng:-58.381592};
            var options = {
                zoom: 12,
                center: bsas,
                mapTypeControl: false,
                streetViewControl: false,
                fullscreenControl: false,
                zoomControl: false
            };

            // New map
            var map = new google.maps.Map(document.getElementById('map'), options);
            var sidebar = document.getElementById('sidebar');

            // Add markers
            <g:each in="${markerList}">
                props = {
                    title:'${it.title}',
                    latitude:${it.latitude},
                    longitude:${it.longitude},
                    visible:${it.visible},
                    name:'${it.category.name}'
                };

                <g:if test="${it.category.iconImage}">
                    props.iconImage = '${it.category.iconImage}';
                </g:if>

                <g:if test="${it.description}">
                    props.description = '${it.description}';
                </g:if>

                if(props.visible){
                    addMarkerToList(props,sidebar);
                    addMarker(props);
                }
            </g:each>

            // Add Marker Function
            function addMarker(props){
                var marker = new google.maps.Marker({
                    position:{lat:props.latitude,lng:props.longitude},
                    map:map,
                    category:props.category
                });

                // Check for customicon
                if(props.iconImage){
                    // Set icon image
                    marker.setIcon(props.iconImage);
                }

                // Check content
                var content = '<h3>' + props.title + '</h3>';
                if(props.description){
                    content = content + props.description;
                }

                var infoWindow = new google.maps.InfoWindow({
                    content:content
                });

                marker.addListener('click', function(){
                    infoWindow.open(map, marker);
                    google.maps.event.addListener(map, 'click', function() {
                        infoWindow.close();
                    });
                });

            }

            function addMarkerToList(props,sidebar){
                var li = document.createElement('li');
                var a = document.createElement('a');
                var h3 = document.createElement('h3');
                var title = document.createTextNode(props.title);
                h3.appendChild(title);
                a.appendChild(h3);
                if(props.description){
                    var description = document.createTextNode(props.description);
                    a.appendChild(description);
                }
                li.appendChild(a);
                sidebar.appendChild(li);
            }
        }
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDP827zHnIce50b1GTB8QPrHOUBFwcsGyw&callback=initMap">
    </script>
</body>
</html>
