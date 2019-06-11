package arquiweb.tp1

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ExtMarkerSpec extends Specification implements DomainUnitTest<ExtMarker> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
