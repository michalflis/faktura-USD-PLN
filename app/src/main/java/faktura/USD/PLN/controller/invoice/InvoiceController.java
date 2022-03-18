package faktura.USD.PLN.controller.invoice;

import faktura.USD.PLN.model.Invoice;
import faktura.USD.PLN.service.InvoiceService;
import faktura.USD.PLN.utils.XmlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Resource> getXmlByDate(@PathVariable String date) throws IOException, JAXBException {

        ByteArrayResource resource = new ByteArrayResource(xmlService.saveToXml(invoiceService.getByDate(date)));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + date + ".xml\"")
                .body(resource);
    }

}
