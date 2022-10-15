package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Marka;
import ru.job4j.cars.repository.BrandRepository;

import java.util.Collection;
@ThreadSafe
@Service
public class BrandService {

    private final BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public Marka findById(int id) {
        return repository.findById(id);
    }

    public Collection<Marka> findAll() {
        return repository.findAll();
    }
}