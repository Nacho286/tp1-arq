package arquiweb.tp1

import grails.validation.ValidationException

import javax.swing.plaf.synth.SynthOptionPaneUI

import static org.springframework.http.HttpStatus.*

class MarkerController {

    MarkerService markerService
    CategoryService categoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        def ourMarkers = markerService.list(params).each { it.source= SourcePlatform.GROUP_1 }
        respond markerService.list(params), model:[markerCount: markerService.count()]
    }

    def markers() {
        //No saco el metodo porq no se donde se esta usando. Pero pase la creacion de la categoria y el mark a Bootstrap.groovy
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

