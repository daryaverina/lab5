package ru.ulstu.is.sbapp.car.model;

import javax.persistence.*;
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

    public Owner(){ }

    public Owner(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
