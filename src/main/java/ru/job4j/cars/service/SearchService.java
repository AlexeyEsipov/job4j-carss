package ru.job4j.cars.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

@ThreadSafe
@Service
@Data
@NoArgsConstructor
public class SearchService {

    private int idCategory;
    private int idBody;
    private int idBrand;
    private int idModel;
    private int idEngine;
    private int idCar;
    private int allCar;
    private int newCar;
    private int oldCar;
}