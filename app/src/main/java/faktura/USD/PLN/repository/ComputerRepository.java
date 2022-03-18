package faktura.USD.PLN.repository;

import faktura.USD.PLN.model.Computer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends PagingAndSortingRepository<Computer, Integer> {
}
