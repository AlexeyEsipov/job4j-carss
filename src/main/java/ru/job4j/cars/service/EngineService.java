package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Dvig;
import ru.job4j.cars.repository.EngineRepository;

import java.util.Collection;
@ThreadSafe
@Service
public class EngineService {

    private final EngineRepository repository;

    public EngineService(EngineRepository repository) {
        this.repository = repository;
    }

    public Dvig findById(int id) {
        return repository.findById(id);
    }

    public Collection<Dvig> findAll() {
        return repository.findAll();
    }
}