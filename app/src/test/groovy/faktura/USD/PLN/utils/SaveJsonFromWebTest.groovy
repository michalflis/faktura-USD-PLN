package faktura.USD.PLN.utils

import spock.lang.Specification

class SaveJsonFromWebTest extends Specification {

    String date = '2019-04-04'


    def "SaveJsonToFile"() {
        setup:
        def SaveJsonFromWeb = new SaveJsonFromWeb()

        when:
        def result = SaveJsonFromWeb.saveJsonToString(date)

        then:
        result == "{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"067/A/NBP/2019\",\"effectiveDate\":\"2019-04-04\",\"mid\":3.8215}]}"
    }
}
