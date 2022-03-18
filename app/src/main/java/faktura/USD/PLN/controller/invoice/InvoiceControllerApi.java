package faktura.USD.PLN.controller.invoice;

import faktura.USD.PLN.model.Invoice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@RequestMapping
@Api(tags = {"invoice-controller"})
public interface InvoiceControllerApi {

    @ApiOperation("Add new invoice")
    @PostMapping(path = "/invoices", produces = {"application/json;charset=UTF-8"})
    ResponseEntity<Invoice> save(@RequestBody Invoice invoice) throws JAXBException;


    @ApiOperation("Get invoice in xml by date")
    @GetMapping(path = "/download/{date}")
    @ResponseBody
    ResponseEntity<Resource> getXmlByDate(@PathVariable String date) throws FileNotFoundException, JAXBException;

}
