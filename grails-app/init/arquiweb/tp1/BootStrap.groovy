package arquiweb.tp1

import arquiweb.tp1.auth.*
import arquiweb.tp1.*
import grails.util.Environment

class BootStrap {
    def authenticateService

    def init = { servletContext ->

        if (Environment.current == Environment.PRODUCTION) {
            //en produ no creo nada
            return
        }

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
                ['name'  : 'Restaurant',
                 approved: true,
                 iconImage: 'https://www.google.com/maps/vt/icon/name=assets/icons/poi/tactile/pinlet_shadow-2-medium.png,assets/icons/poi/tactile/pinlet_outline_v2-2-medium.png,assets/icons/poi/tactile/pinlet-2-medium.png,assets/icons/poi/quantum/pinlet/restaurant_pinlet-2-medium.png&highlight=ff000000,ffffff,db4437,ffffff&color=ff000000?scale=1.3',
                 visible: true]
        )
        def barCategory = new Category(
                ['name'  : 'Bar',
                 approved: true,
                 iconImage: 'https://www.google.com/maps/vt/icon/name=assets/icons/poi/tactile/pinlet_shadow-2-medium.png,assets/icons/poi/tactile/pinlet_outline_v2-2-medium.png,assets/icons/poi/tactile/pinlet-2-medium.png,assets/icons/poi/quantum/pinlet/bar_pinlet-2-medium.png&highlight=ff000000,ffffff,db4437,ffffff&color=ff000000?scale=1.3',
                 visible: true]
        )
        def fastFoodCategory = new Category(
                ['name'  : 'Comida Rapida',
                 approved: true,
                    iconImage: 'https://www.google.com/maps/vt/icon/name=assets/icons/poi/tactile/pinlet_shadow-2-medium.png,assets/icons/poi/tactile/pinlet_outline_v2-2-medium.png,assets/icons/poi/tactile/pinlet-2-medium.png,assets/icons/poi/quantum/pinlet/restaurant_pinlet-2-medium.png&highlight=ff000000,ffffff,ffa100,ffffff&color=ff000000?scale=1.3',
                 visible: true]
        )
        def cafeCategory = new Category(
                ['name'  : 'Cafeteria',
                 approved: true,
                 iconImage: 'https://www.google.com/maps/vt/icon/name=assets/icons/poi/tactile/pinlet_shadow-2-medium.png,assets/icons/poi/tactile/pinlet_outline_v2-2-medium.png,assets/icons/poi/tactile/pinlet-2-medium.png,assets/icons/poi/quantum/pinlet/cafe_pinlet-2-medium.png&highlight=ff000000,ffffff,db4437,ffffff&color=ff000000?scale=1.3',
                 visible: true]
        )
        def schoolCategory = new Category(
                ['name'  : 'Universidad',
                 approved: true,
                 iconImage: 'https://www.google.com/maps/vt/icon/name=assets/icons/poi/tactile/pinlet_shadow-2-medium.png,assets/icons/poi/tactile/pinlet_outline_v2-2-medium.png,assets/icons/poi/tactile/pinlet-2-medium.png,assets/icons/poi/quantum/pinlet/school_pinlet-2-medium.png&highlight=ff000000,ffffff,db4437,ffffff&color=ff000000?scale=1.3',
                 visible: true]
        )

        restoCategory.save(flush: true, failOnError: true)
        barCategory.save(flush: true, failOnError: true)
        fastFoodCategory.save(flush: true, failOnError: true)
        cafeCategory.save(flush: true, failOnError: true)
        schoolCategory.save(flush: true, failOnError: true)

        def restoMarker = new Marker(
                [title      : 'Parrilla Lo de Hugo',
                 latitude   : -34.603722,
                 longitude  : -58.381592,
                 description: 'Parrilla de comida rica y barata',
                 imageLink  : 'https://i.argentino.com.ar/images/2016/1017/964598-parrilla-lo-de-hugo-20161017071109108.jpg',
                 category   : restoCategory,
                 visible    : true,
                 approved   : true])
        
        def barMarker = new Marker(
                [title      : 'Growlers',
                 latitude   : -34.595026,
                 longitude  : -58.387621,
                 description: 'Bar de birras vintage',
                 imageLink  : 'http://salpimenta.com.ar/wp-content/uploads/2016/11/unnamed.jpg',
                 category   : barCategory,
                 visible    : true,
                 approved   : true])

        def mcDonaldsMarker = new Marker(
                [title      : 'McDonalds',
                 latitude   : -34.5589874,
                 longitude  : -58.4456127,
                 description: 'Lugar de comida rapida yankee',
                 imageLink  : 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/McDonalds_Museum.jpg/300px-McDonalds_Museum.jpg',
                 category   : fastFoodCategory,
                 visible    : true,
                 approved   : true])

        def burgerMarker = new Marker(
                [title      : 'El Burger',
                 latitude   : -34.560060,
                 longitude  : -58.458322,
                 category   : fastFoodCategory,
                 visible    : true,
                 approved   : true]
        )

        def starbucksMarker = new Marker(
                [title      : 'StarBucks Belgrano',
                 latitude   : -34.554921,
                 longitude  : -58.449336,
                 description: 'Cafe yankee bien rico',
                 imageLink  : 'http://salpimenta.com.ar/wp-content/uploads/2018/07/starbucks_15_970x597.jpeg',
                 category   : cafeCategory,
                 visible    : true,
                 approved   : true])

        def facuMarker = new Marker(
                [title      : 'Pabellon 1, FCEN, UBA',
                 latitude   : -34.544406,
                 longitude  : -58.439539,
                 description: 'La mejor facultad del condado',
                 imageLink  : 'https://s3-sa-east-1.amazonaws.com/modernabuenosaires/img/obras/galeria/157_1408998107.jpg',
                 category   : schoolCategory,
                 visible    : true,
                 approved   : true])


        restoMarker.save(flush: true, failOnError: true)
        barMarker.save(flush: true, failOnError: true)
        mcDonaldsMarker.save(flush: true, failOnError: true)
        starbucksMarker.save(flush: true, failOnError: true)
        facuMarker.save(flush: true, failOnError: true)
        burgerMarker.save(flush: true, failOnError: true)
    }
    def destroy = {
    }
}
