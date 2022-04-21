package ru.ulstu.is.sbapp.car.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//Один-ко-многим к автомобилям
//Один владелец может иметь несколько машин
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String firstName;
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
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){ return lastName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCar(Car car){
        if(!cars.contains(car))
            cars.add(car);
    }
    public void setCars(List<Car> cars){
        for(var car : cars){
            if(!cars.contains(car))
                cars.add(car);
        }
    }

    public int carsCount(){
        return cars.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(Id, owner.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public String toString() {
        return "Owner{" +
                "id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
