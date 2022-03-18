package faktura.USD.PLN.utils

import faktura.USD.PLN.model.Computer
import faktura.USD.PLN.model.Invoice
import spock.lang.Specification

import java.time.LocalDate

class XmlServiceTest extends Specification {
    def "SaveToXml"() {

        XmlService xmlService = new XmlService()
        given:
        def computer = new Computer(1, "komputer 1", LocalDate.of(2022,4,4), 300 as BigDecimal, 200 as BigDecimal)
        def invoice = new Invoice(UUID.randomUUID(), "2015 - 04 - 18", Arrays.asList(computer, computer, computer))

        when:
        def result = xmlService.saveToXml(invoice)

        then:
        result != null


    }
}
