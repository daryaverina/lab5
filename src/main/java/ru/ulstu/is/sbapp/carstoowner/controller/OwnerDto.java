package ru.ulstu.is.sbapp.carstoowner.controller;

import ru.ulstu.is.sbapp.carstoowner.model.Owner;

import java.util.Objects;

public class OwnerDto {
    private final long id;
    private final String first_name;
    private final String last_name;
    private String cars="";

    public OwnerDto(Owner owner){
        this.id = owner.getId();
        this.first_name = owner.getFirstName();
        this.last_name = owner.getLastName();
        if(Objects.equals(this.getCars(), "")){
            cars = String.valueOf(owner.getCars());
        }
    }

    public long getId() { return id; }

    public String getFirstName() { return first_name; }

    public String getLastName() { return last_name; }

    public String getCars() { return cars; }
}
