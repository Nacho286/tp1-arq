package arquiweb.tp1

import org.grails.web.json.JSONElement

class Marker {

    String title
    double latitude
    double longitude
    String description
    Boolean visible
    Boolean approved
    String imageLink
    String appId = "Flor Nachito JC app"

    static belongsTo = Category
    static hasOne = [category: Category]

    static constraints = {
        title size: 3..255, blank: false, unique: true
        description maxSize: 255, nullable: true
        imageLink nullable: true, type: 'text'
    }

    Marker buildMarkerFromWaltersJson(JSONElement walterJson, Category category){
        this.title = walterJson.title
        this.description = walterJson.description
        this.latitude = walterJson.lat
        this.longitude = walterJson.long
        this.visible = !walterJson.hidden
        this.approved = true
        this.appId = 'Walters app'
        this.category = category

        return this
    }

    Marker buildMarkerFromLugarcitosJson(JSONElement lugarcitosJson, Category category){

        this.title = lugarcitosJson.title
        this.description = lugarcitosJson.description
        this.latitude = lugarcitosJson.latitude
        this.longitude = lugarcitosJson.longitude
        this.visible = lugarcitosJson.visible
        this.approved = lugarcitosJson.approved
        this.imageLink = lugarcitosJson.imageLink
        this.appId = 'Lugarcitos app'
        this.category = category

        return this
    }

    void printMarker(){
        println("Marker:")
        println("title: " + this.title)
        println("latitude: " + this.latitude)
        println("longitude: " + this.longitude)
        println("description: " + this.description)
        println("visible: " + this.visible)
        println("approved: " + this.approved)
        println("imageLink: " + this.imageLink)
        println("appId: " + this.appId)
        println()
    }
}
