package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ThreadSafe
@Repository
public class EngineRepository {
    private final CrudRepository crudRepository;

    public Engine findById(int id) {
        return crudRepository.optional(
                "FROM Engine WHERE id = :id", Engine.class,
                Map.of("id", id)).orElseThrow(NoSuchElementException::new);
    }

    public List<Engine> findAll() {
        return crudRepository.query("FROM Engine", Engine.class);
    }
}