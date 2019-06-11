package arquiweb.tp1

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import org.grails.web.json.JSONElement

import javax.servlet.http.HttpServletResponse

class ExternalController {

    def listMarkers() {
        response.status = HttpServletResponse.SC_OK
        return render(Marker.findAll() as JSON)
    }

    def listCategories() {
        response.status = HttpServletResponse.SC_OK
        return render(Category.findAll() as JSON)
    }

    def searchMarkers() {
        response.status = HttpServletResponse.SC_OK
        String title = params.title
        if (!params.title) {
            title = ""
        }
        return render(Marker.findAllByTitleIlike("%$title%") as JSON)
    }

    def getAllMarkers() {
        List<Marker> markers = Marker.findAll()
        markers = markers + getWalterMarkers() + getLugarcitosMarkers()

        List<Marker> finalMarkers = []
        for(marker in markers){
            if(!BlackList.findByAppIdAndMarkerId(marker.appId,marker.title)){
                finalMarkers.add(marker)
            }
        }

        return finalMarkers
    }

    def getAllCategories() {
        List<Category> categories = Category.findAll()
        categories = categories + getWalterCategories() + getLugarcitosCategories()

        return categories
    }

    def getAllExternalMarkers() {

        return getWalterMarkers() + getLugarcitosMarkers()
    }

    def getAllExternalCategories() {
        return getWalterCategories() + getLugarcitosCategories()
    }

    def getWalterMarkers() {
        RestBuilder rest = new RestBuilder()

        JSONElement markersJson = rest.get('https://arqweb-backend.herokuapp.com/poi/search?') {
        }.json

        JSONElement categoriesJson = rest.get('https://arqweb-backend.herokuapp.com/categories') {
        }.json

        List<Category> walterCategoriesList = []

        for (categoryJson in categoriesJson) {
            Category category = new Category()

            walterCategoriesList.push(category.buildCategoryFromWaltersJson(categoryJson))
        }

        List<Marker> walterMarkersList = []

        for (markerJson in markersJson) {
            Marker marker = new Marker()
            Category category = walterCategoriesList.find {
                it.name == markerJson.category.name && it.appId == 'Walters app'
            }

            walterMarkersList.push(marker.buildMarkerFromWaltersJson(markerJson, category))
        }

        return walterMarkersList
    }

    def getWalterCategories() {
        RestBuilder rest = new RestBuilder()

        JSONElement categoriesJson = rest.get('https://arqweb-backend.herokuapp.com/categories') {
        }.json

        List<Category> walterCategoriesList = []

        for (categoryJson in categoriesJson) {
            Category category = new Category()

            walterCategoriesList.push(category.buildCategoryFromWaltersJson(categoryJson))
        }

        return walterCategoriesList
    }


    def getLugarcitosMarkers() {
        RestBuilder rest = new RestBuilder()

        JSONElement markersJson = rest.get('https://lugarcitos.herokuapp.com/api/markers') {
        }.json

        JSONElement categoriesJson = rest.get('https://lugarcitos.herokuapp.com/api/marker_types') {
        }.json

        List<Category> categories = []

        for (categoryJson in categoriesJson) {
            Category category = new Category()

            categories.push(category.buildCategoryFromLugarcitosJson(categoryJson))
        }


        List<Marker> markersList = []

        for (markerJson in markersJson) {
            Marker marker = new Marker()
            Category category = categories.find {
                it.id == markerJson.category.id && it.appId == 'Lugarcitos app'
            }

            markersList.push(marker.buildMarkerFromLugarcitosJson(markerJson, category))
        }

        return markersList
    }

    def getLugarcitosCategories() {
        RestBuilder rest = new RestBuilder()

        JSONElement categoriesJson = rest.get('https://lugarcitos.herokuapp.com/api/marker_types') {
        }.json

        List<Category> categories = []

        for (categoryJson in categoriesJson) {
            Category category = new Category()

            categories.push(category.buildCategoryFromLugarcitosJson(categoryJson))
        }

        return categories
    }

}
