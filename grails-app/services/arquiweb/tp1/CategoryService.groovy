package arquiweb.tp1

import grails.gorm.services.Service

@Service(Category)
abstract class CategoryService {

    abstract Category get(Serializable id)

    abstract List<Category> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Category save(Category category)

    List<Category> findAll(){
        return Category.findAll()
    }

    List<Category> findAllVisible(){
        return Category.findAllByVisible(true)
    }

    Category findByName(String name){
        return Category.findByName(name)
    }

}