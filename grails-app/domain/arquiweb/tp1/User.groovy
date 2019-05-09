package arquiweb.tp1

class User {

    String login
    String profile

    static constraints = {

        login size: 5..15, blank: false, unique: true
        profile inList: ["plain", "admin"]
    }
}
