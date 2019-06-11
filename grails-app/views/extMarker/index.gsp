<!DOCTYPE html>
<head>
    <g:set var="externalController" bean="externalController"/>
    <g:set var="markersList" value="${externalController.getAllExternalMarkers()}"/>
</head>
<html>

<div>
    <ul>
        <g:each in="${markersList}">
            <li>${it.appId}</li>
            <li>${it.title}</li>
            <li>${it.description}</li>
            <li><button class="btn" onclick="hide()"><h4>Ocultar</h4></button></li>
        </g:each>
    </ul>
</div>
<script>
    function addToBlacklist(app, id) {
        ${raw(remoteFunction(controller:'blackList',action:'putInBlackList',params:'{app:app, markerId:id}',onSuccess:'saved()',onFailure:'error()'))}
    }

    function saved() {

    }

    function error(app, id) {

    }
</script>
</html>