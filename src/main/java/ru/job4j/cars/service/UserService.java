package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepository;
import java.util.Optional;
@ThreadSafe
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> add(User user) {
        return repository.add(user);
    }

    public Optional<User> get(String email, String password) {
        return repository.get(email, password);
    }
}