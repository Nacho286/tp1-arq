<!DOCTYPE html>
<head>
    <g:set var="markerService" bean="markerService"/>
    <g:set var="markersList" value="${markerService.findAll()}"/>
    <g:set var="categoryService" bean="categoryService"/>
    <g:set var="categoryList" value="${categoryService.findAll()}"/>
    <g:set var="homeController" bean="homeController"/>
    <meta name="layout" content="map">
</head>
<body>


    <div class="sidebar">
        <h2>Puntos <br>de interes</h2>
        <ul id="sidebar-list"></ul>
        <div style="position: absolute;bottom: 0;left: 0;"> <g:link controller='logout'>Logout</g:link> </div>
    </div>

    <div id="map"></div>

    <div id="search">
        <a href="javascript:void(0);" onclick="showSearchMenu()">
            <i class="fas fa-search"></i>
        </a>
    </div>

    <div id="filter">
        <a href="javascript:void(0);" onclick="showFilterMenu()">
            <i class="fas fa-filter"></i>
        </a>
    </div>

    <div id="filter-popup" style="display: none">
        <h2>Filter</h2>
        <div class="spacer"></div>
        <g:each in="${categoryList}">
            <input type="checkbox" id="${it.name}" checked>${it.name}<br>
        </g:each>
        <button onclick="fillMap()"><h4>Filter</h4></button>
    </div>

    <div id="search-popup" style="display: none">
        <h2>Search</h2>
        <div class="spacer"></div>
        <input type="text" id="search-name" placeholder="Nombre"><br/>
        <button onclick="searchMap()"><h4>Search</h4></button>
    </div>

    <div class="form-popup" id="myForm">
        <g:form class="form-container">
            <h1>¿Queres agregar un lugar?</h1>

            <label><b>Nombre</b></label><br/>
            <g:textField name="name" placeholder="Nombre del lugar"/><br/>

            <label><b>Descripcion</b></label><br/>
            <g:textField name="description" placeholder="Contanos del lugar"/><br/>

            <label><b>Mandanos una imagen</b></label><br/>
            <g:textField name="imageLink" placeholder="Link"/><br/>

            <label><b>Categoria</b></label><br/>
            <select name="category" id="category">
                <g:each in="${categoryList}" status="i" var="category">
                    <option value="${category.name}">${category.name}</option>
                </g:each>
            </select>
            <g:actionSubmit type="submit" class="btn" controller="marker" action="saveNewMarker" value="Aceptar"/>
            <div>
                <input type="button" class="btn cancel" value="Cerrar" onclick="closeForm()">
            </div>

            <g:hiddenField id="lat" name="lat" value="" display="none"/>
            <g:hiddenField id="long" name="long" value="" display="none"/>
        </g:form>
    </div>

    <script>
        var map;
        var markersByTitle = [];
        var marker

        if(isInArray('ROLE_USER','${homeController.getUserRoles()}')){
            console.log('si')
        }else{
            console.log('no')
        }

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

            // Listen for click on map
            google.maps.event.addListener(map, 'click', function(event){
                // Add marker
                addNewMarker({latitude:event.latLng.lat(),longitude:event.latLng.lng()});
            });

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
            <g:each in="${markersList}">
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

        function searchMap() {
            console.log("busco un lugar ");
            var sidebar = document.getElementsByClassName('sidebar').item(0);
            var nameSearch = document.getElementById('search-name').value;
            deleteAllMarkers();
            var categoriesList = [];

            <g:each in="${categoryList}">
            if ( document.getElementById('${it.name}') !== null && document.getElementById('${it.name}').checked ){
                categoriesList.push('${it.name}');

            }
            </g:each>

            console.log("busco un lugar " + nameSearch);

            // Add markers
            <g:each in="${markersList}">
                if(nameSearch === '${it.title}'){
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
                }
            </g:each>
        }

        function isInArray(value, array) {
            return array.indexOf(value) > -1;
        }

        // Add Marker Function
        function addMarker(props){
            var newMarker = new google.maps.Marker({
                position:{lat:props.latitude,lng:props.longitude},
                map:map,
                category:props.category
            });

            // Check for customicon
            if(props.iconImage){
                // Set icon image
                newMarker.setIcon(props.iconImage);
            }

            // Check content
            var content = '<h3>' + props.title + '</h3>';
            if(props.description){
                content = content + props.description;
            }

            var infoWindow = new google.maps.InfoWindow({
                content:content
            });

            newMarker.addListener('click', function(){
                infoWindow.open(map, marker);
                google.maps.event.addListener(map, 'click', function() {
                    infoWindow.close();
                });
            });

            var dict = {
                title : props.title,
                mapMarker : newMarker
            };
            markersByTitle.push(dict)
        }

        // Add Marker Function
        function addNewMarker(props){
            if ( marker ) {
                marker.setPosition({lat:props.latitude,lng:props.longitude});
            } else {
                marker = new google.maps.Marker({
                    position:{lat:props.latitude,lng:props.longitude},
                    map:map
                });
            }

            // Check for customicon
            if(props.iconImage){
                // Set icon image
                marker.setIcon(props.iconImage);
            }


            marker.addListener('click', function(){
                openForm(props.latitude,props.longitude)
                google.maps.event.addListener(map, 'click', function() {
                    infoWindow.close();
                });
            });


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

        function showSearchMenu() {
            hideOrShowDiv('search-popup')
        }

        function hideOrShowDiv(divId) {
            var x = document.getElementById(divId);
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }

        function openForm(lat,long) {
            document.getElementById("lat").value = lat;
            document.getElementById("long").value = long;
            document.getElementById("myForm").style.display = "block";
        }

        function closeForm() {
            document.getElementById("myForm").style.display = "none";
        }

    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDP827zHnIce50b1GTB8QPrHOUBFwcsGyw&callback=initMap">
    </script>
</body>
</html>
