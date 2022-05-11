package ru.ulstu.is.sbapp.carstoowner.controller;

import ru.ulstu.is.sbapp.carstoowner.model.STO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class STODto {
    private long id;
    private String name;
    private List<Long> cars;
    public STODto() {}
    public STODto(STO sto){
        this.id = sto.getId();
        this.name = sto.getName();
        cars = new ArrayList<>();
        if (sto.getCars() != null) {
            for (var car : sto.getCars()) {
                cars.add(car.getId());
            }
        }
    }



    public long getId() { return id; }

    public String getName() { return name; }

    public List<Long> getCars() { return cars; }

}
