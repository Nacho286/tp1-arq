package arquiweb.tp1

class Marker {

    String title
    String description
    Category category
    Boolean visible

    //TODO: ver si Location es una clase de googleMaps o nuestra
    // Location location

    //TODO: deberian ser varias fotos ? (o videos en req no relevantes)
    // image

    static constraints = {
        title size: 5..255, blank: false
        description maxSize: 255
    }
}
