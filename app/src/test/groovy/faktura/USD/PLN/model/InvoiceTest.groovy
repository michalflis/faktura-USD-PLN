package faktura.USD.PLN.model

import spock.lang.Specification

class InvoiceTest extends Specification {

    def "should build Invoice object"() {
        setup:
        def invoice = new Invoice()

        when:
        def result = invoice

        then:
        result != null
    }
}
