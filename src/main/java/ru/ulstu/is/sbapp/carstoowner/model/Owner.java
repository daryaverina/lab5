package ru.ulstu.is.sbapp.carstoowner.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//Один-ко-многим к автомобилям
//Один владелец может иметь несколько машин
@Entity
public class Owner {
    @Id
    @SequenceGenerator(name = "owner_seq",
            sequenceName = "owner_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq")
    private Long id;
    @NotBlank(message="Owner first name can't be null or empty")
    private String firstName;
    @NotBlank(message="Owner last name can't be null or empty")
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_fk")
    private List<Car> cars = new ArrayList<>();

    public Owner(){ }

    public Owner(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Owner(String firstName, String lastName, List<Car> cars){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){ return lastName; }

    public List<Car> getCars(){
        return cars;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCar(Car car){
        if(!cars.contains(car))
        {
            cars.add(car);
            if(car.getOwner() != this)
            {
                car.setOwner(this);
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

    public void removeAllCars() {
        cars.clear();
    }

    public void updateCar(Long id, Car c) {
        for (var car : cars) {
            if(Objects.equals(car.getId(), c.getId())) {
                car = c;
                return;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return firstName + "$" + lastName;
    }
}
