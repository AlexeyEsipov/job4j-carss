package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Dvig;

import java.util.EnumSet;
import java.util.Set;

@AllArgsConstructor
@ThreadSafe
@Repository
public class EngineRepository {

    public Dvig findById(int id) {
        return Dvig.values()[id];
    }

    public Set<Dvig> findAll() {
        return EnumSet.allOf(Dvig.class);
    }
}
