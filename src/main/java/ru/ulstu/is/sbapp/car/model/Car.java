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
    private Long Id;

    private String model;

    private float price;

    @ManyToOne()
    @JoinColumn(name = "owner_fk")
    private Owner owner;

    @ManyToOne()
    @JoinColumn(name = "sto_fk")
    private STO sto;

    public Car(){
    }

    public Car(String model, float price){
        this.model = model;
        this.price = price;
    }

    public Long getId() {
        return Id;
    }

    public String getModel() {
        return model;
    }

    public float getPrice() {
        return price;
    }

    public Owner getOwner(){ return owner; }

    public STO getSTO(){ return sto; }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
        if(!owner.getCars().contains(this)){
            owner.setCar(this);
        }
    }

    public void setSTO(STO sto){
        this.sto = sto;
        if(!sto.getCars().contains(this)){
            sto.setCar(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(Id, car.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public String toString() {
        return "Car {" +
                " Id=" + Id +
                ", model='" + model + '\'' +
                ", price'" + price + '\'' +
                " }";
    }
}
