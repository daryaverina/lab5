package ru.ulstu.is.sbapp.carstoowner.controller.Owner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.carstoowner.controller.Car.CarDto;
import ru.ulstu.is.sbapp.carstoowner.service.OwnerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/owner")
public class OwnerMvcController {
    private final OwnerService ownerService;

    public OwnerMvcController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public String getOwners(Model model) {
        model.addAttribute("owners",
                ownerService.findAllOwners().stream()
                        .map(OwnerDto::new)
                        .toList());
        return "owner";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editOwner(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("ownerDto", new OwnerDto());
        }
        else {
            model.addAttribute("ownerId", id);
            model.addAttribute("ownerDto", new OwnerDto(ownerService.findOwner(id)));
            model.addAttribute("cars", ownerService.findOwner(id).getCars());
        }
        return "owner-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveOwner(@PathVariable(required = false) Long id,
                          @ModelAttribute @Valid OwnerDto ownerDto,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "owner-edit";
        }
        if (id == null || id <= 0) {
            ownerService.addOwner(ownerDto.getFirstName(), ownerDto.getLastName());
        } else {
            ownerService.updateOwner(id, ownerDto.getFirstName(), ownerDto.getLastName());
        }
        return "redirect:/owner";
    }

    @PostMapping("/delete/{id}")
    public String deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return "redirect:/owner";
    }
}
