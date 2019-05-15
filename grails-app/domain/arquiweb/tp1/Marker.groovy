package arquiweb.tp1

class Marker {

    String title
    double latitude
    double longitude
    String description
    String iconImage
    Boolean visible

    static belongsTo = [category: Category]

    static constraints = {
        title size: 5..255, blank: false
        description maxSize: 255

        description nullable: true
        iconImage nullable: true
    }
}
