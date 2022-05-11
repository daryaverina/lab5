package ru.ulstu.is.sbapp.carstoowner.service;

public class STONotFoundException extends RuntimeException {
    public STONotFoundException(Long id) {
        super(String.format("STO with id [%s] is not found", id));
    }
}
