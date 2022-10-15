package ru.job4j.cars.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "model")
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "brand_id")
    private Marka brand = Marka.BMW;

    public static ModelCar of(String name, Marka brand) {
        ModelCar model = new ModelCar();
        model.name = name;
        model.brand = brand;
        return model;
    }

    public void setBrand(int brand) {
        this.brand = Marka.values()[brand];
    }
}
