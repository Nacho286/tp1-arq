package arquiweb.tp1

import grails.converters.JSON
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
        if(!params.title){
            title = ""
        }
        return render(Marker.findAllByTitleIlike("%$title%") as JSON)
    }

    def getAllMarkers() {
        return Marker.findAll()
    }

    def getAllCategories() {
        return Category.findAll()
    }
}
