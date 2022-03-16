package faktura.USD.PLN.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import faktura.USD.PLN.utils.GetCurrencyRate;
import faktura.USD.PLN.utils.JsonService;
import faktura.USD.PLN.utils.LocalDateAdapter;
import faktura.USD.PLN.utils.SaveJsonFromWeb;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "computers")
@XmlRootElement(name = "komputer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "accountingDate", "costUSD", "costPLN"})
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID generowane przez aplikację", required = true, example = "1")
    @JsonIgnore
    @XmlTransient
    private int computerId;

    @ApiModelProperty(value = "Nazwa komputera", required = true, example = "komputer 1")
    @Column(name = "nazwa")
    @XmlElement(name = "nazwa")
    private String name;

    @ApiModelProperty(value = "Data ksiegowania", required = true, example = "2021-09-30")
    @Column(name = "data_ksiegowania")
    @XmlElement(name = "data_ksiegowania")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate accountingDate;

    @ApiModelProperty(value = "Koszt w USD", required = true, example = "345.00")
    @Column(name = "koszt_USD")
    @XmlElement(name = "koszt_USD")
    private BigDecimal costUSD;

    @ApiModelProperty(value = "Koszt w  PLN", required = true, example = "1485.53")
    @XmlElement(name = "koszt_PLN")
    @Column(name = "koszt_PLN")
    private BigDecimal costPLN;

    @PrePersist
    public void calculatePLNCost() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonService<CurrencyImport> jsonService = new JsonService<>(objectMapper);
        SaveJsonFromWeb saveJsonFromWeb = new SaveJsonFromWeb();
        GetCurrencyRate getCurrencyRate = new GetCurrencyRate(jsonService, saveJsonFromWeb);
        this.costPLN = costUSD.multiply(getCurrencyRate.getRate(accountingDate.toString())).setScale(2, RoundingMode.HALF_UP);
    }
}
