package ru.ulstu.is.sbapp.carstoowner.controller.Car;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.WebConfiguration;
import ru.ulstu.is.sbapp.carstoowner.service.CarService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable Long id) {
        return new CarDto(carService.findCar(id));
    }

    @GetMapping("/")
    public List<CarDto> getCars() {
        return carService.findAllCars().stream()
                .map(CarDto::new)
                .toList();
    }

    @PostMapping("/")
    public CarDto createCar(@RequestBody @Valid CarDto carDto) {
        return carService.addCar(carDto);
    }

    @PatchMapping("/")
    public CarDto updateCar(@RequestBody @Valid CarDto carDto) {
        return carService.updateCar(carDto);
    }

    @DeleteMapping("/{id}")
    public CarDto deleteCar(@PathVariable Long id) {
        return new CarDto(carService.deleteCar(id));
    }
}