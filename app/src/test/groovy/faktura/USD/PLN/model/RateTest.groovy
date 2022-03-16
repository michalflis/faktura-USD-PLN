package faktura.USD.PLN.model

import spock.lang.Specification

class RateTest extends Specification {

    def "should build Rate object"() {
        setup:
        def rate = new Rate()

        when:
        def result = rate

        then:
        result != null
    }
}
