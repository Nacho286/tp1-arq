package arquiweb.tp1

import org.grails.web.json.JSONElement

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

    Category buildCategoryFromWaltersJson(JSONElement walterJson){
        this.name = walterJson.name
        this.approved = !walterJson.hidden
        this.visible = true
        this.appId = 'Walters app'

        return this
    }

    Category buildCategoryFromLugarcitosJson(JSONElement lugarcitosJson){
        this.id = lugarcitosJson.id
        this.name = lugarcitosJson.name
        this.approved = lugarcitosJson.approved
        this.visible = lugarcitosJson.visible
        this.appId = 'Lugarcitos app'

        return this
    }

    void printCategory(){
        println("Category:")
        println("Name: " + this.name)
        println("Icon Image: " + this.iconImage)
        println("Visible: " + this.visible)
        println("Approved: " + this.approved)
        println("AppId: " + this.appId)
        println()
    }
}
