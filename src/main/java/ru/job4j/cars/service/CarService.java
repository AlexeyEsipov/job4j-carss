package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CarRepository;

import java.util.Collection;
@ThreadSafe
@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public Car findById(int id) {
        return repository.findById(id);
    }

    public Collection<Car> findAll() {
        return repository.findAll();
    }

    public void add(Car car) {
        repository.add(car);
    }
}