package faktura.USD.PLN.utils;

import faktura.USD.PLN.model.Invoice;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

import static faktura.USD.PLN.configuration.Configuration.XML_PATH;

@Service
public class XmlService {

    public void saveToXml(Invoice invoice) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Invoice.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(invoice, new File(XML_PATH + invoice.getDate() + ".xml"));
    }
}
