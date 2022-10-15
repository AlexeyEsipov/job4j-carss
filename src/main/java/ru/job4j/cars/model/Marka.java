package ru.job4j.cars.model;

import lombok.Getter;

@Getter
public enum Marka {
    BMW("BMW", "0"),
    AUDI("Audi", "1"),
    FORD("Ford", "2"),
    HYUNDAI("Hyundai", "3");
    final String name;
    final String id;
    Marka(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
