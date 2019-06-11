package arquiweb.tp1

class BlackListController {

    BlackList putInBlackList(String app, String markerId) {
        if (!BlackList.findByAppIdAndMarkerId(app, markerId)) {
            BlackList blackList = new BlackList(
                    appId: app,
                    markerId: markerId
            )

            blackList.save(flush: true)
            return blackList
        }
        return null
    }

    void removeFromBlackList(String app, String markerId) {
        BlackList blackList = BlackList.findByAppIdAndMarkerId(app, markerId)
        if (blackList) {
            blackList.delete(flush: true)
        }
    }

    BlackList putInBlackListFromMarker(Marker marker) {
        return putInBlackList(marker.appId, marker.id)
    }

    List<BlackList> findAll() {
        return BlackList.findAll()
    }
}
