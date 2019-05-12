package arquiweb.tp1

import grails.gorm.services.Service

@Service(Marker)
interface MarkerService {

    Marker get(Serializable id)

    List<Marker> list(Map args)

    Long count()

    void delete(Serializable id)

    Marker save(Marker marker)

}