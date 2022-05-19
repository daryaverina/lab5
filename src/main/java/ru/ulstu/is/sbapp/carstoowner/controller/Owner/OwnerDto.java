package ru.ulstu.is.sbapp.carstoowner.controller.Owner;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.carstoowner.model.Owner;

import java.util.*;

public class OwnerDto {
    private long id;
    private String first_name;
    private String last_name;
    private Map<Long, String> cars;

    public OwnerDto() {}
    public OwnerDto(Owner owner){
        this.id = owner.getId();
        this.first_name = owner.getFirstName();
        this.last_name = owner.getLastName();
        cars = new HashMap<>();
        if (owner.getCars() != null) {
            for (var car : owner.getCars()) {
                cars.put(car.getId(), car.getModel());
            }
        }
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() { return id; }

    public String getFirstName() { return first_name; }

    public String getLastName() { return last_name; }

    public Map<Long, String> getCars() { return cars; }

    public void setFirstName(String first_name) { this.first_name = first_name; }

    public void setLastName(String last_name) { this.last_name = last_name; }
}
