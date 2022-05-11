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
    public Car addCar(String model, float price, String firstNameOw, String lastNameOw, String nameSTO) {
        if(!StringUtils.hasText(model) || !StringUtils.hasText(firstNameOw) || !StringUtils.hasText(lastNameOw) || !StringUtils.hasText(nameSTO)) {
            throw new IllegalArgumentException("Car data is null or empty");
        }
        var owner = ownerService.findOwnerByFIO(firstNameOw, lastNameOw);
        var sto = stoService.findSTOByName(nameSTO);
        var car = new Car(model, price);
        car.setOwner(owner);
        car.setSTO(sto);
        validatorUtil.validate(car);
        return carRepository.save(car);
    }

    @Transactional
    public CarDto addCar(CarDto carDto) {
        var name = Arrays.stream(carDto.getOwner().split("\\$")).toArray();
        return new CarDto(addCar(carDto.getModel(), carDto.getPrice(), name[0].toString(), name[1].toString(), carDto.getSto()));
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
    public Car updateCar(Long id, String model, float price, Long idSTO, Long idOwner) {
        if(!StringUtils.hasText(model)) {
            throw new IllegalArgumentException("Car data is null or empty");
        }
        final Car currentcar = findCar(id);
        var owner = ownerService.findOwner(idOwner);
        var sto = stoService.findSTO(idSTO);
        currentcar.setModel(model);
        currentcar.setPrice(price);
        if (currentcar.getSTO().getId().equals(idSTO)) {
            currentcar.getSTO().updateCar(id, currentcar);
        }
        else {
            currentcar.getSTO().removeCar(id);
            currentcar.setSTO(sto);
        }

        if (currentcar.getOwner().getId().equals(idOwner)) {
            currentcar.getOwner().updateCar(id, currentcar);
        }
        else {
            currentcar.getOwner().removeCar(id);
            currentcar.setOwner(owner);
        }

        validatorUtil.validate(currentcar);
        return carRepository.save(currentcar);
    }

    @Transactional
    public CarDto updateCar(CarDto carDto) {
        return new CarDto(updateCar(carDto.getId(), carDto.getModel(), carDto.getPrice(), name[0].toString(), name[1].toString(), carDto.getSto()));
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
