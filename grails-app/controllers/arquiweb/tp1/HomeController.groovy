package arquiweb.tp1

import grails.converters.JSON
import groovy.transform.CompileStatic
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder

@CompileStatic
@Secured('isAuthenticated()')
class HomeController {

    static responseFormats = ['json']

    SpringSecurityService springSecurityService

    def index() {
        [name: loggedUsername()]
    }

    String loggedUsername() {
        if ( springSecurityService.principal == null ) {
            return null
        }
        if ( springSecurityService.principal instanceof String ) {
            return springSecurityService.principal
        }
        if ( springSecurityService.principal instanceof GrailsUser ) {
            return ((GrailsUser) springSecurityService.principal).username
        }
        null
    }

    def getUserRoles() {
        def roleNames
        if ( loggedIn ) {
            // will be a List of String
            roleNames = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        } else {
            roleNames = ['ROLE_GUEST']
        }

        return roleNames[0]
    }
}