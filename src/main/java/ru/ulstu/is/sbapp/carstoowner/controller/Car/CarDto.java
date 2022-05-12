package ru.ulstu.is.sbapp.carstoowner.controller.Car;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.carstoowner.model.Car;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CarDto {
    private long id;
    @NotBlank(message = "Model can't be null or empty")
    private String model;
    private float price;
    private long owner;
    private long sto;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() { return id; }

    public String getModel() { return model; }
    //public void setModel(String model) { this.model = model; }

    public float getPrice() { return price; }
    //public void setPrice(float price) { this.price = price; }

    public long getSto(){ return sto; }

    public long getOwner() { return owner; }
}
