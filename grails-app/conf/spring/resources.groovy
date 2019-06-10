import arquiweb.tp1.BlackListController
import arquiweb.tp1.HomeController
import arquiweb.tp1.ExternalController

import arquiweb.tp1.auth.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    homeController(HomeController)
    externalController(ExternalController)
    blackListController(BlackListController)
}
