package arquiweb.tp1

import grails.gorm.services.Service

@Service(Marker)
abstract class MarkerService {
    CategoryService categoryService

    abstract Marker get(Serializable id)

    abstract List<Marker> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Marker save(Marker marker)

    List<Marker> findAll() {
        return Marker.findAll()
    }

    List<Marker> findByName(String name){
        return Marker.findAllByTitle(name)
    }

    List<Marker> findAllByCategoryName(String categoryName) {
        Category category = categoryService.findByName(categoryName)
        if (category) {
            return Marker.findAllByCategory(category)
        } else {
            return null
        }
    }

    void saveNewMarker(def params) {

        Category category = categoryService.findByName(params.category)
        Marker marker = new Marker(title: params.name,
        description:params.description,
        visible: true,
        category: category,
        latitude: Double.valueOf(params.lat),
        longitude: Double.valueOf(params.long),
        imageLink: params.imageLink,
        approved: params.approved ?: false,
        appId: params.appId ?: "Flor Nachito JC app")

        save(marker)
    }

}