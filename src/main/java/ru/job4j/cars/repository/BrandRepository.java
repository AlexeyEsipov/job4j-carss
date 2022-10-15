package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Marka;

import java.util.*;

@AllArgsConstructor
@ThreadSafe
@Repository
public class BrandRepository {

    public Marka findById(int id) {
        return Marka.values()[id];
    }

    public Set<Marka> findAll() {
        return EnumSet.allOf(Marka.class);
    }
}
