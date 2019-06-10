package arquiweb.tp1

class BlackListController {

    BlackList putInBlackList(String app, Long markerId){
        BlackList blackList = new BlackList(
                appId: app,
                markerId: markerId
        )

        blackList.save(flush:true)

        return blackList
    }

    BlackList putInBlackListFromMarker(Marker marker){
        return putInBlackList(marker.appId,marker.id)
    }
}
