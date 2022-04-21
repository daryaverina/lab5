package ru.ulstu.is.sbapp.car.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.car.model.Car;
import ru.ulstu.is.sbapp.car.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.findCar(id);
    }

    @GetMapping("/")
    public List<Car> getCars() {
        return carService.findAllCars();
    }

    @PostMapping("/")
    public Car createCar(@RequestParam("model") String model,
                         @RequestParam("price") float price) {
        return carService.addCar(model, price);
    }

    @PatchMapping("/{id}")
    public Car updateCar(@PathVariable Long id,
                         @RequestParam("model") String model,
                         @RequestParam("price") float price) {
        return carService.updateCar(id, model, price);
    }

    @DeleteMapping("/{id}")
    public Car deleteCar(@PathVariable Long id) {
        return carService.deleteCar(id);
    }
}