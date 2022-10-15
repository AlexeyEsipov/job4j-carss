package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Body;
import ru.job4j.cars.repository.BodyRepository;

import java.util.Collection;
@ThreadSafe
@Service
public class BodyService {

    private final BodyRepository repository;

    public BodyService(BodyRepository repository) {
        this.repository = repository;
    }

    public Collection<Body> findAll() {
        return repository.findAll();
    }

    public Body findById(int id) {
        return repository.findById(id);
    }

    public Collection<Body> findBodyByCategory(int categoryId) {
        return repository.findBodyByCategory(categoryId);
    }
}