package faktura.USD.PLN.model

import spock.lang.Specification

class CurrencyImportTest extends Specification {

    def "should build CurrencyImport object"() {
        setup:
        def currencyImport = new ComputerTest()

        when:
        def result = currencyImport

        then:
        result != null
    }
}