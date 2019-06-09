package arquiweb.tp1

enum SourcePlatform{
    GROUP_1, // nuestro grupo
    GROUP_2,
    GROUP_3
}

class Marker {

    String title
    double latitude
    double longitude
    String description
    Boolean visible
    Boolean approved
    String imageLink
    SourcePlatform source

    static belongsTo = Category
    static hasOne = [category: Category]

    static constraints = {
        title size: 3..255, blank: false, unique: true
        description maxSize: 255, nullable: true
        imageLink nullable: true, type: 'text'
        approved nullable: true
        source nullable: true
    }
}
