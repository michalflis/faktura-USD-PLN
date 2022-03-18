package faktura.USD.PLN.service;

import faktura.USD.PLN.model.Computer;
import faktura.USD.PLN.model.Invoice;
import faktura.USD.PLN.repository.ComputerRepository;
import faktura.USD.PLN.repository.GenericRepository;
import faktura.USD.PLN.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ComputerService implements GenericRepository<Computer> {

    private final ComputerRepository computerRepository;

    @Override
    public Computer save(Computer computer) {
        return computerRepository.save(computer);
    }

    @Override
    public Computer getById(UUID id) {
        return null;
    }

    @Override
    public List<Computer> getAll() {
        List<Computer> computerList = new ArrayList<>();
        computerRepository.findAll().forEach(computerList::add);
        return computerList;
    }

    public List<Computer> getByDate(LocalDate date) {
        return getAll()
                .stream()
                .filter(computer -> computer.getAccountingDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public void clear() {
        computerRepository.deleteAll();
    }
}


