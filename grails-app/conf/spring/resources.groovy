import arquiweb.tp1.HomeController

import arquiweb.tp1.auth.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    homeController(HomeController)
}
