package faktura.USD.PLN.model

import spock.lang.Specification

class ComputerTest extends Specification {

    def "should build Computer object"() {
        setup:
        def computer = new Computer()

        when:
        def result = computer

        then:
        result != null
    }
}
