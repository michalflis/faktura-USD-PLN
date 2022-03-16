package faktura.USD.PLN.model;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyImport {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}
