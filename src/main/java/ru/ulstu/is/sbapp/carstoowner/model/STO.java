package ru.ulstu.is.sbapp.carstoowner.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//один ко многим с Car
//в одном СТО много автомобилей
@Entity
public class STO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message="STO name can't be null or empty")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sto_fk")
    private List<Car> cars = new ArrayList<>();

    public STO() {
    }

    public STO(String name) {
        this.name = name;
    }

    public STO(String name, List<Car> cars){
        this.name = name;
        this.cars = cars;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public List<Car> getCars(){
        return cars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCar(Car car){
        if(!cars.contains(car))
        {
            cars.add(car);
            if(car.getSTO() != this)
            {
                car.setSTO(this);
            }
        }
    }

    public Car removeCar(Long carId) {
        for (var car : cars) {
            if (Objects.equals(car.getId(), carId)){
                cars.remove(car);
                return car;
            }
        }
        return null;
    }

    public void updateCar(Long id, Car c) {
        for (var car : cars) {
            if(Objects.equals(car.getId(), c.getId())) {
                car = c;
                return;
            }
        }
    }

    public void removeAllCars() {
        cars.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        STO sto = (STO) o;
        return Objects.equals(Id, sto.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public String toString() {
        return name;
    }
}
