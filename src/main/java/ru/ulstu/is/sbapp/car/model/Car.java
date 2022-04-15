package ru.ulstu.is.sbapp.car.model;

import javax.persistence.*;
import java.util.Objects;
//Многие-к-одному к STO
//Много автомобилей в одном СТО
//Многие-к-одному к Owner
//Один владелец может иметь несколько автомобилей
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

    private float price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sto_id")
    private Owner owner;

    public Car(){ }

    public Car(String model, float price){
        this.model = model;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price'" + price + '\'' +
                '}';
    }
}
