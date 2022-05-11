package ru.ulstu.is.sbapp.carstoowner.service;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long id) {
        super(String.format("Car with id [%s] is not found", id));
    }
}
