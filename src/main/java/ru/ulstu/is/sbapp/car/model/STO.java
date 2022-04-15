package ru.ulstu.is.sbapp.car.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
//один ко многим с Car
//в одном СТО много автомобилей
public class STO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String name;
    @OneToMany(mappedBy = "sto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;

    public STO() {
    }

    public STO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCars(Car car) {
        cars.add(car);
    }

    public Car getCars(int id) {
        return cars.remove(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        STO sto = (STO) o;
        return Objects.equals(id, sto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "STO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
