package ru.ulstu.is.sbapp.carstoowner.controller;

import ru.ulstu.is.sbapp.carstoowner.model.Car;

import java.util.Objects;

public class CarDto {
    private long id;
    private String model;
    private float price;
    private String owner = "";
    private String sto ="";

    /*
    * можно ли хранить Id ownera и sto или сами объекты?*/
    public CarDto() {

    }

    public CarDto(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.price = car.getPrice();
        if(Objects.equals(this.getOwner(), "")){
            owner = String.valueOf(car.getOwner());
        }
        if(Objects.equals(this.getSto(), "")){
            sto = String.valueOf(car.getSTO());
        }
    }

    public long getId() { return id; }

    public String getModel() { return model; }

    public float getPrice() { return price; }

    public String getSto(){ return sto; }

    public String getOwner() { return owner; }
}
