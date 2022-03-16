package faktura.USD.PLN.utils

import com.fasterxml.jackson.databind.ObjectMapper
import faktura.USD.PLN.model.CurrencyImport
import spock.lang.Specification

class GetCurrencyRateTest extends Specification {

    String date1 = '2019-04-04'
    String date2 = '2022-01-03'
    def objectMapper = new ObjectMapper()
    def saveJsonFromWeb = new SaveJsonFromWeb()
    def jsonService = new JsonService<CurrencyImport>(objectMapper)
    def getCurrencyRate = new GetCurrencyRate(jsonService, saveJsonFromWeb)


    def "Should get rate from 2019-04-04"() {
        when:
        def result = getCurrencyRate.getRate(date1)

        then:
        result == 3.8215
    }

    def "Should get rate from 2022-01-03"() {
        when:
        def result = getCurrencyRate.getRate(date2)

        then:
        result == 4.0424
    }
}
