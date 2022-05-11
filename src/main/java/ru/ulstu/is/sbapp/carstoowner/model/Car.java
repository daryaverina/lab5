package ru.ulstu.is.sbapp.carstoowner.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
//Многие-к-одному к STO
//Много автомобилей в одном СТО
//Многие-к-одному к Owner
//Один владелец может иметь несколько автомобилей
@Entity
public class Car {
    @Id
    @SequenceGenerator(name = "car_seq",
    sequenceName = "car_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    private Long id;
    @NotBlank (message="Car model can't be null or empty")
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
        return id;
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

    public void removeOwner() {
        if(owner.removeCar(getId()) != null)
        {
            owner.removeCar(getId());
        }
        owner = null;
    }

    public void removeSTO() {
        if(sto.removeCar(getId()) != null)
        {
            sto.removeCar(getId());
        }
        sto = null;
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
        return model + "$" + price;
    }
}
