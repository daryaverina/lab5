package ru.ulstu.is.sbapp.car.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.car.model.Owner;
import ru.ulstu.is.sbapp.car.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public Owner getOwner(@PathVariable Long id) {
        return ownerService.findOwner(id);
    }

    @GetMapping("/")
    public List<Owner> getOwners() {
        return ownerService.findAllOwners();
    }

    @PostMapping("/")
    public Owner createOwner(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
        return ownerService.addOwner(firstName, lastName);
    }

    @PatchMapping("/{id}")
    public Owner updateOwner(@PathVariable Long id,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
        return ownerService.updateOwner(id, firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public Owner deleteOwner(@PathVariable Long id) {
        return ownerService.deleteOwner(id);
    }
}