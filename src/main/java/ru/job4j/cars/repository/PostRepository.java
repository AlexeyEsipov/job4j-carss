package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ThreadSafe
@Repository
public class PostRepository {

    private static final String PART_QUERY = "SELECT DISTINCT post FROM Post post "
            + "JOIN FETCH post.car car "
            + "JOIN FETCH car.category category "
            + "JOIN FETCH car.body body "
            + "JOIN FETCH car.model model ";

    private static final String CREATED_DESC = "ORDER BY post.created DESC";
    private final CrudRepository crudRepository;

    public List<Post> findPostLastDay() {
        return crudRepository.query(PART_QUERY
                        + "WHERE post.created BETWEEN :yesterday AND :today "
                        + CREATED_DESC, Post.class,
                Map.of("yesterday", Timestamp.valueOf(LocalDateTime.now().minusDays(1)),
                        "today", Timestamp.valueOf(LocalDateTime.now()))
        );
    }

    public List<Post> findPostCategoryAndSold(String categoryName, boolean sold) {
        return crudRepository.query(
                PART_QUERY
                        + "WHERE category.name = :categoryName AND post.sold = :sold "
                        + CREATED_DESC, Post.class,
                Map.of("categoryName", categoryName, "sold", sold)
        );
    }

    public List<Post> findMyAds(String email) {
        return crudRepository.query(
                PART_QUERY
                        + "WHERE post.user.email = :userEmail "
                        + CREATED_DESC, Post.class,
                Map.of("userEmail", email)
        );
    }

    public List<Post> findAdCategoryAndBodyAndBrandAndModel(int categoryId,
                                                            int bodyId,
                                                            int brandId,
                                                            int modelId) {
        String carBrand = String.format("AND car.brand = %d ", brandId);
        return crudRepository.query(
                PART_QUERY
                        + "WHERE category.id = :catId AND body.id = :bodyId "
                        + carBrand
                        + "AND model.id = :modelId ", Post.class,
                Map.of("catId", categoryId, "bodyId", bodyId,  "modelId", modelId)
        );
    }

    public Post findById(int id) {
        return crudRepository.optional(
                "FROM Post WHERE id = :id", Post.class,
                Map.of("id", id)).orElseThrow(NoSuchElementException::new);
    }

    public List<Post> findAll() {
        return crudRepository.query(
                PART_QUERY
                        + CREATED_DESC, Post.class
        );
    }

    public List<Post> findNewCar(boolean newCar, boolean sold) {
        return crudRepository.query(
                PART_QUERY
                        + "WHERE post.newCar = :newCar AND post.sold = :sold "
                        + CREATED_DESC, Post.class,
                Map.of("newCar", newCar, "sold", sold)
        );
    }

    public List<Post> findSoldAll(boolean sold) {
        return crudRepository.query(
                PART_QUERY
                        + "WHERE post.sold = :sold "
                        + CREATED_DESC, Post.class,
                Map.of("sold", sold)
        );
    }

    public Post add(Post post) {
        crudRepository.run(session -> session.save(post));
        return post;
    }

    public void delete(int id) {
        crudRepository.run("DELETE Post post "
                        + "WHERE post.id = :id",
                Map.of("id", id)
        );
    }

    public void setSold(int id) {
        crudRepository.run("UPDATE Post post "
                        + "SET post.sold = :sold "
                        + "WHERE post.id = :id",
                Map.of("id", id, "sold", true));
    }

    public void update(Post post) {
        crudRepository.run(session -> session.merge(post));
    }

    public List<Post> findAdIsPhoto() {
        return crudRepository.query(
                PART_QUERY
                        + "WHERE post.photo.size > 0 "
                        + CREATED_DESC, Post.class
        );
    }

    public List<Post> findAdWithCategory(String categoryName) {
        return crudRepository.query(
                PART_QUERY
                        + "WHERE category.name = :categoryName "
                        + CREATED_DESC, Post.class,
                Map.of("categoryName", categoryName)
        );
    }
}
