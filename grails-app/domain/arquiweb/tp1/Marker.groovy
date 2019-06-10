package arquiweb.tp1

class Marker {

    String title
    double latitude
    double longitude
    String description
    Boolean visible
    Boolean approved
    String imageLink
    Long appId = 8295

    static belongsTo = Category
    static hasOne = [category: Category]

    static constraints = {
        title size: 3..255, blank: false, unique: true
        description maxSize: 255, nullable: true
        imageLink nullable: true, type: 'text'
    }
}
