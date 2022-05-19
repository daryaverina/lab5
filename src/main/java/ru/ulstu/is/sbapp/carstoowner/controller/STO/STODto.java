package ru.ulstu.is.sbapp.carstoowner.controller.STO;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.carstoowner.model.STO;

import java.util.*;

public class STODto {
    private long id;
    private String name;
    private Map<Long, String> cars;
    public STODto() {}
    public STODto(STO sto){
        this.id = sto.getId();
        this.name = sto.getName();
        cars = new HashMap<>();
        if (sto.getCars() != null) {
            for (var car : sto.getCars()) {
                cars.put(car.getId(), car.getModel());
            }
        }
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() { return id; }

    public String getName() { return name; }

    public Map<Long, String> getCars() { return cars; }

    public void setName(String name) { this.name = name; }
}
