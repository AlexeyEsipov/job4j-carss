package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;

import java.util.List;

public class HbnCar {
    public static void main(String[] args) {


    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
        try {
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            for (Post post : findAllPost(Post.class, sf)) {
                System.out.println(post.getId() + " " + post.getName()
                        + " " + post.getCar().getEngine().getName() + " " + post.getCar().getModel().getName());
            }
    }  catch (Exception e) {
        e.printStackTrace();
    } finally {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}

    public static <T> T create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }

    public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
//        List<T> list = session.createQuery("from " + cl.getName() + " c where c.name = 'Petr Arsentev'", cl).list();
        List<T> list = session.createQuery("from " + cl.getName() + " c where c.body = 2", cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public static <T> List<T> findAllPost(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
//        List<T> list = session.createQuery("from " + cl.getName() + " c where c.name = 'Petr Arsentev'", cl).list();
        List<T> list = session.createQuery("SELECT DISTINCT post FROM Post post "
                + "JOIN FETCH post.car car "
                + "JOIN FETCH car.category category "
                + "JOIN FETCH car.body body "
                + "JOIN FETCH car.model model "
                + "WHERE category.id = 1 AND body.id = 2 "
                + "AND car.brand = 0 AND model.id = 3 ", cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}

