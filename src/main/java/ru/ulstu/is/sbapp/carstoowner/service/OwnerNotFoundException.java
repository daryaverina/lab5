package ru.ulstu.is.sbapp.carstoowner.service;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(Long id) {
        super(String.format("Owner with is [%s] is not found", id));
    }
}
