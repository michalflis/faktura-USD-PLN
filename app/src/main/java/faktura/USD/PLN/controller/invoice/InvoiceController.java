package faktura.USD.PLN.controller.invoice;

import faktura.USD.PLN.model.Invoice;
import faktura.USD.PLN.service.InvoiceService;
import faktura.USD.PLN.utils.XmlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import static faktura.USD.PLN.configuration.Configuration.XML_PATH;

@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class InvoiceController implements InvoiceControllerApi {

    private final InvoiceService invoiceService;
    private final XmlService xmlService;

    @Override
    public ResponseEntity<Invoice> save(@RequestBody Invoice invoice) {
        log.debug("Adding new invoice to invoiceDatabase");
        return ResponseEntity.ok()
                .body(invoiceService.save(invoice));
    }

    @Override
    public ResponseEntity<List<Invoice>> getAll() {
        log.debug("Getting all invoices from Database");
        return ResponseEntity.ok()
                .body(invoiceService.getAll());
    }

    @Override
    public ResponseEntity<Invoice> getById(@PathVariable UUID id) {
        log.debug("Getting invoice Id: {} from Database", id);
        try {
            return ResponseEntity.ok().body(invoiceService.getById(id));
        } catch (Exception e) {
            log.error("Exception: {} occurred while getting invoice Id: {} from Database", e, id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<Resource> getXmlById(@PathVariable UUID id) throws FileNotFoundException, JAXBException {
        xmlService.saveToXml(invoiceService.getById(id));
        File file = new File(XML_PATH + id + ".xml");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".xml\"")
                .body(resource);
    }
}
