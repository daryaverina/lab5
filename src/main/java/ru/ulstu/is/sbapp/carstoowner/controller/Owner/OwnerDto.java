package ru.ulstu.is.sbapp.carstoowner.controller.Owner;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.carstoowner.model.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OwnerDto {
    private long id;
    private String first_name;
    private String last_name;
    private List<Long> cars;

    public OwnerDto() {}
    public OwnerDto(Owner owner){
        this.id = owner.getId();
        this.first_name = owner.getFirstName();
        this.last_name = owner.getLastName();
        cars = new ArrayList<>();
        if (owner.getCars() != null) {
            for (var car : owner.getCars()) {
                cars.add(car.getId());
            }
        }
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() { return id; }

    public String getFirstName() { return first_name; }

    public String getLastName() { return last_name; }

    public List<Long> getCars() { return cars; }
}
