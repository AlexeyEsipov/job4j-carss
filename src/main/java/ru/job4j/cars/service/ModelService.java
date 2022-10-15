package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.ModelCar;
import ru.job4j.cars.repository.ModelRepository;

import java.util.Collection;
@ThreadSafe
@Service
public class ModelService {

    private final ModelRepository repository;

    public ModelService(ModelRepository repository) {
        this.repository = repository;
    }

    public Collection<ModelCar> findAll() {
        return repository.findAll();
    }

    public ModelCar findById(int id) {
        return repository.findById(id);
    }

    public Collection<ModelCar> findModelByBrand(int brandId) {
        return repository.findModelByBrand(brandId);
    }
}