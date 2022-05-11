package ru.ulstu.is.sbapp.carstoowner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.carstoowner.controller.CarDto;
import ru.ulstu.is.sbapp.carstoowner.model.Car;
import ru.ulstu.is.sbapp.carstoowner.repository.CarRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final ValidatorUtil validatorUtil;
    private final OwnerService ownerService;
    private final STOService stoService;

    public CarService(CarRepository carRepository, ValidatorUtil validatorUtil, OwnerService ownerService,
                      STOService stoService) {
        this.carRepository = carRepository;
        this.validatorUtil = validatorUtil;
        this.stoService = stoService;
        this.ownerService = ownerService;
    }

    @Transactional
    public Car addCar(String model, float price, long ownerId, long stoId) {
        if(!StringUtils.hasText(model) || ownerId == 0 || stoId == 0) {
            throw new IllegalArgumentException("Car data is null or empty");
        }
        var owner = ownerService.findOwner(ownerId);
        var sto = stoService.findSTO(stoId);
        var car = new Car(model, price);
        car.setOwner(owner);
        car.setSTO(sto);
        validatorUtil.validate(car);
        return carRepository.save(car);
    }

    @Transactional
    public CarDto addCar(CarDto carDto) {
        return new CarDto(addCar(carDto.getModel(), carDto.getPrice(), carDto.getOwner(), carDto.getSto()));
    }


    @Transactional(readOnly = true)
    public Car findCar(Long id) {
        final Optional<Car> car = carRepository.findById(id);
        return car.orElseThrow(() -> new CarNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Transactional
    public Car updateCar(Long id, String model, float price, Long ownerId, Long stoId) {
        if(!StringUtils.hasText(model)) {
            throw new IllegalArgumentException("Car data is null or empty");
        }
        final Car currentcar = findCar(id);
        var owner = ownerService.findOwner(ownerId);
        var sto = stoService.findSTO(stoId);
        currentcar.setModel(model);
        currentcar.setPrice(price);
        if (currentcar.getOwner().getId().equals(ownerId)) {
            currentcar.getOwner().updateCar(id, currentcar);
        }
        else {
            currentcar.getOwner().removeCar(id);
            currentcar.setOwner(owner);
        }

        if (currentcar.getSTO().getId().equals(stoId)) {
            currentcar.getSTO().updateCar(id, currentcar);
        }
        else {
            currentcar.getSTO().removeCar(id);
            currentcar.setSTO(sto);
        }
        validatorUtil.validate(currentcar);
        return carRepository.save(currentcar);
    }

    @Transactional
    public CarDto updateCar(CarDto carDto) {
        return new CarDto(updateCar(carDto.getId(), carDto.getModel(), carDto.getPrice(), carDto.getOwner(), carDto.getSto()));
    }

    @Transactional
    public Car deleteCar(Long id) {
        final Car currentCar = findCar(id);
        carRepository.delete(currentCar);
        return currentCar;
    }

    @Transactional
    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
