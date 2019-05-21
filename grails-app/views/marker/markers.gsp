<!DOCTYPE html>
<head>
    <g:set var="markerService" bean="markerService"/>
    <g:set var="categoryService" bean="categoryService"/>
    <g:set var="categoryList" value="${categoryService.findAll()}"/>
    <meta name="layout" content="map">
</head>
<body>
    <div class="sidebar">
        <h2>Puntos <br>de interes</h2>
        <ul id="sidebar-list"></ul>
        <div style="position: absolute;bottom: 0;left: 0;"> <g:link controller='logout'>Logout</g:link> </div>
    </div>

    <div id="map"></div>

    <div id="filter">
        <a href="javascript:void(0);" onclick="showFilterMenu()">
            <i class="fas fa-filter"></i>
        </a>
    </div>

    <div id="filter-popup" style="display: none">
        <h2>Filter</h2>
        <div id="spacer"></div>
        <g:each in="${categoryList}">
            <input type="checkbox" id="${it.name}" checked>${it.name}<br>
        </g:each>
        <button onclick="fillMap()"><h4>Filter</h4></button>
    </div>

    <script>
        var map;
        var markersByTitle = [];

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

            var dict = {
                title : props.title,
                mapMarker : marker
            };
            markersByTitle.push(dict)
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
            for (var i = 0; i < markersByTitle.length; i++) {
                marker = markersByTitle[i];
                //Removing from sidebar-list
                removeElement(marker.title);
                //Removing from map
                (marker.mapMarker).setMap(null);
            }
            markersByTitle = [];
        }

        function removeElement(id) {
            var elem = document.getElementById(id);
            return elem.parentNode.removeChild(elem);
        }

        function showFilterMenu() {
            hideOrShowDiv('filter-popup')
        }

        function hideOrShowDiv(divId) {
            var x = document.getElementById(divId);
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }

    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDP827zHnIce50b1GTB8QPrHOUBFwcsGyw&callback=initMap">
    </script>
</body>
</html>
