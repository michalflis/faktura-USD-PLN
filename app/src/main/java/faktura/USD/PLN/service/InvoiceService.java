package faktura.USD.PLN.service;

import faktura.USD.PLN.model.Invoice;
import faktura.USD.PLN.repository.GenericRepository;
import faktura.USD.PLN.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Service
public class InvoiceService implements GenericRepository<Invoice> {

    private final InvoiceRepository invoiceRepository;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getById(UUID id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice Id: " + id + " does not exist"));
    }

    public Invoice getByDate(String date) {
        return getAll().stream()
                .filter(invoice -> invoice.getDate().equals(date))
                .findAny()
                .get();
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceRepository.findAll().forEach(invoiceList::add);
        return invoiceList;
    }

    @Override
    public boolean delete(UUID id) {
        if (invoiceRepository.findById(id).isPresent()) {
            invoiceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        invoiceRepository.deleteAll();
    }
}


