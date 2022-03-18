package faktura.USD.PLN.utils;

import faktura.USD.PLN.model.Invoice;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class XmlService {

    StringWriter stringWriter = new StringWriter();

    public byte[] saveToXml(Invoice invoice) throws JAXBException, IOException {
        JAXBContext ctx = JAXBContext.newInstance(Invoice.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(invoice, stringWriter);
        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }


}