package arquiweb.tp1

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MarkerServiceSpec extends Specification {

    MarkerService markerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Marker(...).save(flush: true, failOnError: true)
        //new Marker(...).save(flush: true, failOnError: true)
        //Marker marker = new Marker(...).save(flush: true, failOnError: true)
        //new Marker(...).save(flush: true, failOnError: true)
        //new Marker(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //marker.id
    }

    void "test get"() {
        setupData()

        expect:
        markerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Marker> markerList = markerService.list(max: 2, offset: 2)

        then:
        markerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        markerService.count() == 5
    }

    void "test delete"() {
        Long markerId = setupData()

        expect:
        markerService.count() == 5

        when:
        markerService.delete(markerId)
        sessionFactory.currentSession.flush()

        then:
        markerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Marker marker = new Marker()
        markerService.save(marker)

        then:
        marker.id != null
    }
}
