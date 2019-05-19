package arquiweb.tp1

class Marker {

    String title
    double latitude
    double longitude
    String description
    Boolean visible

    static belongsTo = Category
    static hasOne = [category: Category]

    static constraints = {
        title size: 5..255, blank: false
        description maxSize: 255, nullable: true
    }
}
