package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Category;
import ru.job4j.cars.repository.CategoryRepository;

import java.util.Collection;
@ThreadSafe
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(int id) {
        return repository.findById(id);
    }

    public Collection<Category> findAll() {
        return repository.findAll();
    }
}