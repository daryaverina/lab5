package ru.ulstu.is.sbapp.carstoowner.controller;

import ru.ulstu.is.sbapp.carstoowner.model.STO;

import java.util.Objects;

public class STODto {
    private final long id;
    private final String name;
    private String cars="";

    public STODto(STO sto){
        this.id = sto.getId();
        this.name = sto.getName();
        if(Objects.equals(this.getCars(), "")){
            cars = String.valueOf(sto.getCars());
        }
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public String getCars() { return cars; }

}
