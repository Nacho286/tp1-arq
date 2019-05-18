package tp1

import arquiweb.tp1.auth.UserDataService

class BootStrap {
    def authenticateService

    def init = { servletContext ->
        println 'Initializing users..'
        //UserDataService.save('kermit',  'frog', true, false, false, false)
        //new User(username: 'kermit', password: 'frog').save()
    }
    def destroy = {
    }
}
