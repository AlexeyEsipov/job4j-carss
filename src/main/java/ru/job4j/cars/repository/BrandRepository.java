package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ThreadSafe
@Repository
public class BrandRepository {
    private final CrudRepository crudRepository;

    public Brand findById(int id) {
        return crudRepository.optional(
                "FROM Brand WHERE id = :id", Brand.class,
                Map.of("id", id)).orElseThrow(NoSuchElementException::new);
    }

    public List<Brand> findAll() {
        return crudRepository.query(
                "SELECT DISTINCT brand FROM Brand brand "
                        + "JOIN FETCH brand.models model "
                        + "ORDER BY brand.name ASC ", Brand.class);
    }
}
