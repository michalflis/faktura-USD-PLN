package faktura.USD.PLN.controller.invoice;

import faktura.USD.PLN.controller.invoice.InvoiceControllerApi;
import faktura.USD.PLN.model.Invoice;
import faktura.USD.PLN.service.InvoiceService;
import faktura.USD.PLN.utils.XmlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


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
    public ResponseEntity<Resource> getXmlByDate(@PathVariable String date) throws FileNotFoundException, JAXBException {
        xmlService.saveToXml(invoiceService.getByDate(date));
        File file = new File(XML_PATH + date + ".xml");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + date + ".xml\"")
                .body(resource);
    }
}
