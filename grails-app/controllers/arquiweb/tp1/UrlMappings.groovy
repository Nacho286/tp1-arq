package arquiweb.tp1

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/" {
            controller = "Marker"
            action = "markers"
        }

        "/marker/saveNewMarker" {
            controller = "Marker"
            action = "saveNewMarker"
        }
        "500"(view: '/error')
        "404"(view: '/notFound')



        "/markers"(controller: "External", parseRequest: true){
            action = [GET: 'listMarkers', OPTIONS: "options"]
        }

        "/categories"(controller: "External", parseRequest: true){
            action = [GET: 'listCategories', OPTIONS: "options"]
        }

        "/extmarker"(controller: "External", parseRequest: true){
            action = [GET: 'listCategories', OPTIONS: "options"]
        }

        "/search"(controller: "External", parseRequest: true){
            action = [GET: 'searchMarkers', OPTIONS: "options"]
        }
    }
}
