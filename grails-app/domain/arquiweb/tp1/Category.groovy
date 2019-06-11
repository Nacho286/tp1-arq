package arquiweb.tp1

class Category {

    String name
    //icon ENUM?
    String iconImage
    Boolean visible = true
    Boolean approved = false
    String appId = "Flor Nachito JC app"

    static hasMany = [markers: Marker]

    static constraints = {
        name blank: false, unique: true
        iconImage nullable: true, type: 'text'
    }
}
