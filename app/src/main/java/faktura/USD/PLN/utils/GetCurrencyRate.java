package faktura.USD.PLN.utils;

import faktura.USD.PLN.model.CurrencyImport;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Data
public class GetCurrencyRate {

    private final JsonService<CurrencyImport> jsonService;
    private final SaveJsonFromWeb saveJsonFromWeb;

    public BigDecimal getRate(String date) throws Exception {

        CurrencyImport currencyImport = jsonService.convertToObject(saveJsonFromWeb.saveJsonToString(date), CurrencyImport.class);
        return new BigDecimal(
                currencyImport
                        .getRates()
                        .stream()
                        .findFirst()
                        .get()
                        .getMid()
        );
    }
}
