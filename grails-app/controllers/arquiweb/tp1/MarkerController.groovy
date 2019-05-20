package arquiweb.tp1

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MarkerController {

    MarkerService markerService
    CategoryService categoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static namespace = "marker"

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond markerService.list(params), model:[markerCount: markerService.count()]
    }

    def markers() {
        Category restoCategory = Category.findByName('Restaurantes')
        if(!restoCategory){
            restoCategory = new Category(
                    ['name':'Restaurantes',
                     approved:true]
            )
            categoryService.save(restoCategory)
        }

        Marker restoMarker = Marker.findByTitle('Resto')
        if(!restoMarker){
            Marker marker = new Marker(
                    [title:'Resto',
                     latitude:-34.603722,
                     longitude:-58.381592,
                     description:'Prueba',
                     visible:true])
            marker.setCategory(restoCategory)
            markerService.save(marker)
        }
    }

    def saveNewMarker(){
        markerService.saveNewMarker(params.name,params.description,params.category,Double.valueOf(params.lat),Double.valueOf(params.long))
        redirect(uri: "/")
    }

    def show(Long id) {
        respond markerService.get(id)
    }

    def create() {
        respond new Marker(params)
    }

    def save(Marker marker) {
        if (marker == null) {
            notFound()
            return
        }

        try {
            markerService.save(marker)
        } catch (ValidationException e) {
            respond marker.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'marker.label', default: 'Marker'), marker.id])
                redirect marker
            }
            '*' { respond marker, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond markerService.get(id)
    }

    def update(Marker marker) {
        if (marker == null) {
            notFound()
            return
        }

        try {
            markerService.save(marker)
        } catch (ValidationException e) {
            respond marker.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'marker.label', default: 'Marker'), marker.id])
                redirect marker
            }
            '*'{ respond marker, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        markerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'marker.label', default: 'Marker'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'marker.label', default: 'Marker'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
