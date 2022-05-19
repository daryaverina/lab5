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
    private String owner_first_name;
    private String owner_last_name;
    private String sto_name;

    public CarDto() { }

    public CarDto(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.price = car.getPrice();
        if (car.getOwner() != null) {
            owner = car.getOwner().getId();
            owner_first_name = car.getOwner().getFirstName();
            owner_last_name = car.getOwner().getLastName();
        }
        if (car.getSTO() != null) {
            sto = car.getSTO().getId();
            sto_name = car.getSTO().getName();
        }
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() { return id; }

    public String getModel() { return model; }

    public float getPrice() { return price; }

    public long getSto(){ return sto; }

    public long getOwner() { return owner; }

    public void setModel(String model) { this.model = model; }

    public void setPrice(float price) { this.price = price; }

    public void setSto(long sto){ this.sto = sto; }

    public void setOwner(long owner) { this.owner = owner; }

    public String getOwner_first_name() {
        return owner_first_name;
    }

    public String getOwner_last_name() {
        return owner_last_name;
    }

    public String getSto_name() {
        return sto_name;
    }
}
