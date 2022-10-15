package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Body;
import java.util.*;

@AllArgsConstructor
@ThreadSafe
@Repository
public class BodyRepository {
    private final CrudRepository crudRepository;

    public Body findById(int id) {
        return crudRepository.optional(
                "FROM Body WHERE id = :id", Body.class,
                Map.of("id", id)).orElseThrow(NoSuchElementException::new);
    }

    public List<Body> findAll() {
        return crudRepository.query("FROM Body", Body.class);
    }

    public List<Body> findBodyByCategory(int categoryId) {
        return crudRepository.query(
                        "SELECT DISTINCT body FROM Body body "
                                + "JOIN FETCH body.category category "
                                + "WHERE category.id = :id "
                                + "ORDER BY body.type ASC ", Body.class,
                Map.of("id", categoryId)
        );
    }
}