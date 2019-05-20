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

    void saveNewMarker(String title, String description,String categoryName, double lat, double longi) {
        Category category = categoryService.findByName(categoryName)
        Marker marker = new Marker(title: title,
        description:description,
        visible: false,
        category: category,
        latitude: lat,
        longitude: longi)

        marker.save()
    }

}