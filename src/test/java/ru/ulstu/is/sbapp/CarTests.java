package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.car.model.Car;
import ru.ulstu.is.sbapp.car.model.Owner;
import ru.ulstu.is.sbapp.car.model.STO;
import ru.ulstu.is.sbapp.car.service.CarService;
import ru.ulstu.is.sbapp.car.service.OwnerService;
import ru.ulstu.is.sbapp.car.service.STOService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class CarTests {
    private static final Logger log = LoggerFactory.getLogger(CarTests.class);

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private CarService carService;
    @Autowired
    private STOService stoService;

    @Test
    void testCreate() {
        log.info("testCreate");
        ownerService.deleteAllOwners();
        carService.deleteAllCars();
        stoService.deleteAllSTOs();

        Owner owner1 = ownerService.addOwner("Иван", "Петров");
        Owner owner2 = ownerService.addOwner("Кирилл", "Сергеев");

        Car car1 = carService.addCar("VW POLO", 999999);
        Car car2 = carService.addCar("Skoda Octavia", 1500000);
        List<Car> cars = carService.findAllCars();

        STO sto1 = stoService.addSTO("СТО№1");
        STO sto2 = stoService.addSTO("СТО№2");

        stoService.setCar(sto1.getId(), car1);
        stoService.setCar(sto1.getId(), car2);

        ownerService.setCar(owner1.getId(), car1);
        ownerService.setCar(owner2.getId(), car2);

        log.info("Owner1 cars count: " + ownerService.carsCount(owner1.getId()));
        log.info("Owner2 cars count: " + ownerService.carsCount(owner2.getId()));

        log.info("Car1 owner is: " + carService.findCar(car1.getId()).getOwner().getFirstName() + " " + carService.findCar(car1.getId()).getOwner().getLastName() + ". Car1 sto is: " + carService.findCar(car1.getId()).getSTO().getName());
        log.info("Car2 owner is: " + carService.findCar(car2.getId()).getOwner().getFirstName() + " " + carService.findCar(car2.getId()).getOwner().getLastName() + ". Car2 sto is: " + carService.findCar(car2.getId()).getSTO().getName());

        log.info("STO1 cars count is: " + stoService.findSTO(sto1.getId()).carsCount());
        log.info("STO2 cars count is: " + stoService.findSTO(sto2.getId()).carsCount());

        Assertions.assertEquals(cars.size(), 2);
    }

    @Test
    void testCarRead() {
        log.info("testCarRead");
        carService.deleteAllCars();
        final Car car = carService.addCar("VW POLO", 999999);
        log.info(car.toString());
        final Car findCar = carService.findCar(car.getId());
        log.info(findCar.toString());
        Assertions.assertEquals(car, findCar);
    }

    @Test
    void testCarReadNotFound() {
        log.info("testCarReadNotFound");
        carService.deleteAllCars();
        Assertions.assertThrows(EntityNotFoundException.class, () -> carService.findCar(-1L));
    }

    @Test
    void testCarReadAll() {
        log.info("testCarReadAll");
        carService.deleteAllCars();
        carService.addCar("VW POLO", 999999);
        carService.addCar("Skoda Octavia", 1500000);
        final List<Car> cars = carService.findAllCars();
        log.info(cars.toString());
        Assertions.assertEquals(cars.size(), 2);
    }

    @Test
    void testCarsReadAllEmpty() {
        log.info("testCarsReadAllEmpty");
        carService.deleteAllCars();
        final List<Car> cars = carService.findAllCars();
        log.info(cars.toString());
        Assertions.assertEquals(cars.size(), 0);
    }
}
