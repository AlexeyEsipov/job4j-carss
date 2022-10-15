package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.util.Collection;
import java.util.List;
@ThreadSafe
@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findPostLastDay() {
        return repository.findPostLastDay();
    }

    public List<Post> findPostCategoryAndSold(String categoryName, boolean sold) {
        return repository.findPostCategoryAndSold(categoryName, sold);
    }

    public Post findById(int id) {
        return repository.findById(id);
    }

    public Collection<Post> findAll() {
        return repository.findAll();
    }

    public Collection<Post> findSoldAll(boolean sold) {
        return repository.findSoldAll(sold);
    }

    public Collection<Post> findNewCar(boolean newCar, boolean sold) {
        return repository.findNewCar(newCar, sold);
    }

    public void add(Post post) {
        repository.add(post);
    }



    public void delete(int id) {
        repository.delete(id);
    }

    public void setSold(int id) {
        repository.setSold(id);
    }

    public List<Post> findAdCategoryAndBodyAndBrandAndModel(int categoryName,
                                                            int bodyType,
                                                            int brandName,
                                                            int modelName) {
        return repository.findAdCategoryAndBodyAndBrandAndModel(
                categoryName, bodyType, brandName, modelName);
    }

    public List<Post> findMyAds(String email) {
        return repository.findMyAds(email);
    }
}