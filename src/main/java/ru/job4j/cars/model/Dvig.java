package ru.job4j.cars.model;

import lombok.Getter;

@Getter
public enum Dvig {
    V4("V41", "0"),
    V6("V66", "1"),
    V8("V86", "2"),
    V12("V122", "3");
    final String name;
    final String id;
    Dvig(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
