package ru.ulstu.is.sbapp.carstoowner.controller.STO;

import com.fasterxml.jackson.annotation.JsonProperty;
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


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() { return id; }

    public String getName() { return name; }

    public List<Long> getCars() { return cars; }

}
