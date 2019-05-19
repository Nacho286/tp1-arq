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

    List<Marker> findAllByCategoryName(String categoryName) {
        Category category = categoryService.findByName(categoryName)
        if (category) {
            return Marker.findAllByCategory(category)
        } else {
            return null
        }
    }

    void saveNewMarker(String name, String description) {
        Marker marker = new Marker(name: name,
        description:description,
        visible: false)

        marker.save()
    }

}