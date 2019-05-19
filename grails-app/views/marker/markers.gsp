<!DOCTYPE html>
<head>
    <g:set var="markerService" bean="markerService"/>
    <g:set var="categoryService" bean="categoryService"/>
    <g:set var="categoryList" value="${categoryService.findAll()}"/>
    <meta name="layout" content="map">
</head>
<body>
    <div id="prueba"></div>
    <div class="sidebar">
        <h2>Menu</h2>
        <ul id="sidebar-list"></ul>
    </div>

    <div id="map"></div>

    <a href="javascript:void(0);" onclick="filter()"><i class="fas fa-filter" id="filter"></i></a>
    <div id="filter-popup">
        <h2>Filter</h2>
        <g:each in="${categoryList}">
            <input type="checkbox" value="${it.name}" checked>${it.name}<br>
        </g:each>
        <input type="button" value="Filter" onclick="fillMap()">
    </div>

    <script>
        var map;
        var markers = [];

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
            map = new google.maps.Map(document.getElementById('map'), options);

            fillMap();
        }

        function fillMap() {
            var sidebar = document.getElementsByClassName('sidebar').item(0);
            var categoriesList = [];

            deleteAllMarkers();

            <g:each in="${categoryList}">
                if ( document.getElementById('${it.name}') !== null && document.getElementById('${it.name}').checked ){
                    categoriesList.push('${it.name}');
                }
            </g:each>

            // Add markers
            <g:each in="${markerService.findAll()}">
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

                if(${it.visible} && ${it.category.visible}){
                    if(isInArray('${it.category.name}',categoriesList)){
                        addMarkerToList(props,sidebar);
                        addMarker(props);
                    }
                }
            </g:each>
        }

        function isInArray(value, array) {
            return array.indexOf(value) > -1;
        }

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

            markers.push(marker)
        }

        function addMarkerToList(props,sidebar){
            var ul = document.getElementById('sidebar-list');
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
            li.setAttribute('id',props.title);
            ul.appendChild(li);
            sidebar.appendChild(ul);
        }

        function deleteAllMarkers() {
            var ul = document.getElementById('sidebar-list');
            //Loop through all the markers and remove
            var marker;
            for (var i = 0; i < markers.length; i++) {
                //Removing from sidebar-list
                marker = document.getElementById(markers[i].title);
                ul.removeChild(marker);
                //Removing from map
                marker.setMap(null);
            }
            markers = [];
        }

    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDP827zHnIce50b1GTB8QPrHOUBFwcsGyw&callback=initMap">
    </script>
</body>
</html>
