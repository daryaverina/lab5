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
        Car car3 = carService.addCar("Nissan Skyline", 2000000);
        Car car4 = carService.addCar("Ford Focus", 1200000);

        STO sto1 = stoService.addSTO("СТО№1");
        STO sto2 = stoService.addSTO("СТО№2");

        stoService.setCar(sto1.getId(), car1);
        stoService.setCar(sto1.getId(), car2);
        stoService.setCar(sto1.getId(), car4);
        stoService.setCar(sto2.getId(), car3);

        ownerService.setCar(owner1.getId(), car1);
        ownerService.setCar(owner2.getId(), car2);
        ownerService.setCar(owner2.getId(), car3);
        ownerService.setCar(owner2.getId(), car4);

        var carFind1 = carService.findCar(car1.getId());
        log.info(car1.toString());
        log.info(carFind1.toString());
        Assertions.assertEquals(car1, carFind1);

        var carFind4 = carService.findCar(car4.getId());
        log.info(car4.toString());
        log.info(carFind4.toString());
        Assertions.assertEquals(car4, carFind4);

        var ownerFind1 = ownerService.findOwner(owner1.getId());
        log.info(owner1.toString());
        log.info(ownerFind1.toString());
        Assertions.assertEquals(owner1, ownerFind1);

        var stoFind2 = stoService.findSTO(sto2.getId());
        log.info(sto2.toString());
        log.info(stoFind2.toString());
        Assertions.assertEquals(sto2, stoFind2);

        log.info("Owner1 cars count: " + ownerService.carsCount(owner1.getId()));
        log.info("Owner2 cars count: " + ownerService.carsCount(owner2.getId()));

        log.info("Car1 owner is: " + carService.findCar(car1.getId()).getOwner().getFirstName() + " " + carService.findCar(car1.getId()).getOwner().getLastName() + ". Car1 sto is: " + carService.findCar(car1.getId()).getSTO().getName());
        log.info("Car2 owner is: " + carService.findCar(car2.getId()).getOwner().getFirstName() + " " + carService.findCar(car2.getId()).getOwner().getLastName() + ". Car2 sto is: " + carService.findCar(car2.getId()).getSTO().getName());
        log.info("Car3 owner is: " + carService.findCar(car3.getId()).getOwner().getFirstName() + " " + carService.findCar(car3.getId()).getOwner().getLastName() + ". Car3 sto is: " + carService.findCar(car3.getId()).getSTO().getName());
        log.info("Car4 owner is: " + carService.findCar(car4.getId()).getOwner().getFirstName() + " " + carService.findCar(car4.getId()).getOwner().getLastName() + ". Car4 sto is: " + carService.findCar(car4.getId()).getSTO().getName());

        log.info("STO1 cars count is: " + stoService.findSTO(sto1.getId()).carsCount());
        log.info("STO2 cars count is: " + stoService.findSTO(sto2.getId()).carsCount());

        Assertions.assertEquals(carService.findAllCars().size(), 4);
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
