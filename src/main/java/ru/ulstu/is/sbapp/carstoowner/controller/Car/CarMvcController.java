package ru.ulstu.is.sbapp.carstoowner.controller.Car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.carstoowner.controller.Owner.OwnerDto;
import ru.ulstu.is.sbapp.carstoowner.controller.STO.STODto;
import ru.ulstu.is.sbapp.carstoowner.service.CarService;
import ru.ulstu.is.sbapp.carstoowner.service.OwnerService;
import ru.ulstu.is.sbapp.carstoowner.service.STOService;

import javax.validation.Valid;

@Controller
@RequestMapping("/car")
public class CarMvcController {
    private final CarService carService;
    private final OwnerService ownerService;
    private final STOService stoService;

    public CarMvcController(CarService carService, OwnerService ownerService, STOService stoService) {
        this.carService = carService;
        this.ownerService = ownerService;
        this.stoService = stoService;
    }

    @GetMapping
    public String getCars(Model model) {
        model.addAttribute("cars",
                carService.findAllCars().stream()
                        .map(CarDto::new)
                        .toList());
        return "car";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editCar(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("carDto", new CarDto());
        }
        else {
            model.addAttribute("carId", id);
            model.addAttribute("carDto", new CarDto(carService.findCar(id)));
        }
        model.addAttribute("owners", ownerService.findAllOwners().stream().map(OwnerDto::new).toList());
        model.addAttribute("stos", stoService.findAllSTOs().stream().map(STODto::new).toList());
        return "car-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveCar(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid CarDto carDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "car-edit";
        }
        if (id == null || id <= 0) {
            carService.addCar(carDto.getModel(), carDto.getPrice(), carDto.getOwner(), carDto.getSto());
        } else {
            carService.updateCar(id, carDto.getModel(), carDto.getPrice(), carDto.getOwner(), carDto.getSto());
        }
        return "redirect:/car";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/car";
    }
}
