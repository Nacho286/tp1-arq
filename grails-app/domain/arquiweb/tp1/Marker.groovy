package arquiweb.tp1

class Marker {

    String title
    double latitude
    double longitude
    String description
    Boolean visible
    String imageLink

    static belongsTo = Category
    static hasOne = [category: Category]

    static constraints = {
        title size: 5..255, blank: false
        description maxSize: 255, nullable: true
        imageLink nullable: true
    }
}
