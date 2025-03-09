package ca.cal.tp2.Repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T save(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    T update(T entity);
    void delete(T entity);
    void deleteById(int id);
}
