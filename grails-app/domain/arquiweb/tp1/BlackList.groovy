package arquiweb.tp1

class BlackList {

    Long markerId
    String appId

    static constraints = {
        markerId nullable: false, unique: true
        appId nullable: false, blank: false
    }
}
