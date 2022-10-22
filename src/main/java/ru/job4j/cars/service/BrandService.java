package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.BrandRepository;

import java.util.Collection;
@ThreadSafe
@Service
public class BrandService {

    private final BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public Brand findById(int id) {
        return repository.findById(id);
    }

    public Collection<Brand> findAll() {
        return repository.findAll();
    }
}