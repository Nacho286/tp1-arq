package arquiweb.tp1

class Category {

    String name
    //icon ENUM?
    Boolean visible
    Boolean approved

    static constraints = {
        name size: 5..100, blank: false, unique: true
    }
}
