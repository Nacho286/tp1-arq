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
}
