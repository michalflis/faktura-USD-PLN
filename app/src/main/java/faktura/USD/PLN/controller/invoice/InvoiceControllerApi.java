package faktura.USD.PLN.controller.invoice;

import faktura.USD.PLN.model.Invoice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/invoices", produces = {"application/json;charset=UTF-8"})
@Api(tags = {"invoice-controller"})
public interface InvoiceControllerApi {

    @ApiOperation("Add new invoice")
    @PostMapping
    ResponseEntity<Invoice> save(@RequestBody Invoice invoice) throws JAXBException;

    @ApiOperation("Get list of all invoices")
    @GetMapping
    ResponseEntity<List<Invoice>> getAll();

    @ApiOperation("Get invoice by Id")
    @GetMapping(path = "/{id}")
    ResponseEntity<Invoice> getById(@PathVariable UUID id);

    @ApiOperation("Get invoice in xml by Id")
    @GetMapping(path = "/download/{id}")
    @ResponseBody
    ResponseEntity<Resource> getXmlById(@PathVariable UUID id) throws FileNotFoundException, JAXBException;

}
