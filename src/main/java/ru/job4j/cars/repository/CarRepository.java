package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ThreadSafe
@Repository
public class CarRepository {
    private final CrudRepository crudRepository;

    public Car findById(int id) {
        return crudRepository.optional(
                "FROM Car WHERE id = :id", Car.class,
                Map.of("id", id)).orElseThrow(NoSuchElementException::new);
    }

    public List<Car> findAll() {
        return crudRepository.query(
                "SELECT DISTINCT car FROM Car car "
                        + "JOIN FETCH car.engine engine "
                        + "JOIN FETCH car.brand brand "
                        + "JOIN FETCH car.body body "
                        + "JOIN FETCH car.category category ", Car.class
        );
    }

    public Car add(Car car) {
        crudRepository.run(session -> session.save(car));
        return car;
    }
}