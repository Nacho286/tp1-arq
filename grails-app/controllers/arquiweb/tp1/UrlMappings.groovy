package arquiweb.tp1

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }



        "/marker/saveNewMarker" {
            controller = "Marker"
            action = "saveNewMarker"
        }

        "/category/saveNewCategory" {
            controller = "Category"
            action = "saveNewCategory"
        }

        "/" {
            controller = "Marker"
            action = "markers"
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
