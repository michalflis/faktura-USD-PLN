package faktura.USD.PLN.repository;

import faktura.USD.PLN.model.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, UUID> {
}
