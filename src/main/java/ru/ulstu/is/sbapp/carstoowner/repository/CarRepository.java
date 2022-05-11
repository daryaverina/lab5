package ru.ulstu.is.sbapp.carstoowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.carstoowner.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
