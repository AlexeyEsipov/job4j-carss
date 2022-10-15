package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Category;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ThreadSafe
@Repository
public class CategoryRepository {
    private final CrudRepository crudRepository;

    public Category findById(int id) {
        return crudRepository.optional(
                "FROM Category WHERE id = :id", Category.class,
                Map.of("id", id)).orElseThrow(NoSuchElementException::new);
    }

    public List<Category> findAll() {
        return crudRepository.query("FROM Category", Category.class);
    }
}