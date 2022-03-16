package faktura.USD.PLN.repository;

import java.util.List;
import java.util.UUID;

public interface GenericRepository<T> {

    T save(T object);

    T getById(UUID id);

    List<T> getAll();

    boolean delete(UUID id);

    void clear();
}
