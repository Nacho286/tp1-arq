package arquiweb.tp1

class Category {

    String name
    //icon ENUM?
    String iconImage
    Boolean approved

    static hasMany = [markers: Marker]

    static constraints = {
        name size: 5..100, blank: false, unique: true
        iconImage nullable: true
    }
}
