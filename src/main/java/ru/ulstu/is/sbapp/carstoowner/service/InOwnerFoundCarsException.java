package ru.ulstu.is.sbapp.carstoowner.service;

public class InOwnerFoundCarsException extends Exception{
    public InOwnerFoundCarsException(String errorMessage) {
        super(errorMessage);
    }
}
