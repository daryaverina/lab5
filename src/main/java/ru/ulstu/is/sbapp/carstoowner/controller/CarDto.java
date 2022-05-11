package ru.ulstu.is.sbapp.carstoowner.controller;

import ru.ulstu.is.sbapp.carstoowner.model.Car;

import java.util.Objects;

public class CarDto {
    private long id;
    private String model;
    private float price;
    private long owner;
    private long sto;

    /*
    * можно ли хранить Id ownera и sto или сами объекты?*/
    public CarDto() { }

    public CarDto(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.price = car.getPrice();
        if (car.getOwner() != null) {
            owner = car.getOwner().getId();
        }
        if (car.getSTO() != null) {
            sto = car.getSTO().getId();
        }
    }

    public long getId() { return id; }

    public String getModel() { return model; }

    public float getPrice() { return price; }

    public long getSto(){ return sto; }

    public long getOwner() { return owner; }
}
