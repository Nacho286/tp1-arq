package arquiweb.tp1

import grails.gorm.services.Service

@Service(Category)
abstract class CategoryService {

    abstract Category get(Serializable id)

    abstract List<Category> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Category save(Category category)

    Category findByName(String name){
        return Category.findByName(name)
    }

}