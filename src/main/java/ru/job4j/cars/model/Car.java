package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "engine_id")
    private Dvig engine = Dvig.V4;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "brand_id")
    private Marka brand;

    @ManyToOne
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private ModelCar model = ModelCar.of("first", Marka.BMW);

    @ManyToOne
    @JoinColumn(name = "body_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"))
    private Body body;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "CATEGORY_ID_FK"))
    private Category category;

    public Car() {
        this.brand = Marka.BMW;
    }

    public void setEngine(int en) {
            this.engine = Dvig.values()[en];
        }

    public void setBrand(int brand) {
        this.brand = Marka.values()[brand];
    }
}
