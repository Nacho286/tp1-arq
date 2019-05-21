package arquiweb.tp1

import arquiweb.tp1.auth.*
import arquiweb.tp1.*

class BootStrap {
    def authenticateService

    def init = { servletContext ->
        println 'Initializing users..'

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def nachoUser = new User(username: 'nacho', password: 'password')
        def jcUser = new User(username: 'juancruz', password: 'password')
        def florUser = new User(username: 'flor', password: 'password')

        nachoUser.save(flush: true, failOnError: true)
        jcUser.save(flush: true, failOnError: true)
        florUser.save(flush: true, failOnError: true)

        UserRole.create nachoUser, adminRole, true
        UserRole.create jcUser, userRole, true
        UserRole.create florUser, adminRole, true

        println 'Initializing categories...'

        def restoCategory = new Category(
                ['name'  : 'Restaurantes',
                 approved: true]
        )
        restoCategory.save(flush: true, failOnError: true)


        def markerPrueba = new Marker(
                [title      : 'Resto',
                 latitude   : -34.603722,
                 longitude  : -58.381592,
                 description: 'Restorante 1',
                 visible    : true])
        markerPrueba.setCategory(restoCategory)
        markerPrueba.save(flush: true, failOnError: true)
    }
    def destroy = {
    }
}