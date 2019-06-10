package arquiweb.tp1

class BlackList {

    Long markerId
    String app

    static constraints = {
        markerId nullable: false, unique: true
        app nullable: false, blank: false
    }
}
