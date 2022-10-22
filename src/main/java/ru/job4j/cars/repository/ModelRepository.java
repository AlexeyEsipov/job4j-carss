package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.ModelCar;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ThreadSafe
@Repository
public class ModelRepository {
    private final CrudRepository crudRepository;

    public ModelCar findById(int id) {
        return crudRepository.optional(
                "FROM ModelCar WHERE id = :id", ModelCar.class,
                Map.of("id", id)).orElseThrow(NoSuchElementException::new);
    }

    public List<ModelCar> findAll() {
        return crudRepository.query("FROM ModelCar", ModelCar.class);
    }

    public List<ModelCar> findModelByBrand(int brandId) {
        return crudRepository.query(
                "SELECT DISTINCT model FROM ModelCar model "
                        + "JOIN FETCH model.brand brand "
                        + "WHERE brand.id = :id "
                        + "ORDER BY model.name ASC ", ModelCar.class,
                Map.of("id", brandId)
        );
    }
}