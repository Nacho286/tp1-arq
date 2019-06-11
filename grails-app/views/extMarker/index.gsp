<!DOCTYPE html>
<head>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
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
            <li><button class="btn" onclick="addToBlacklist('${it.appId}', '${it.title}')"><h4>Ocultar</h4></button>
            <li><button class="btn" onclick="removeFromBlacklist('${it.appId}', '${it.title}')"><h4>Desocultar</h4></button>
            </li>
        </g:each>
    </ul>
</div>

<g:javascript>

    function addToBlacklist(app, id) {
        jQuery.ajax({
            url: '${g.createLink(controller: 'blackList', action: 'putInBlackList')}',
            data: {app: app, markerId: id}
        });
    }

    function removeFromBlacklist(app, id) {
        jQuery.ajax({
            url: '${g.createLink(controller: 'blackList', action: 'removeFromBlackList')}',
            data: {app: app, markerId: id}
        });
    }

</g:javascript>
</html>