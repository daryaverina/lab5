package ru.ulstu.is.sbapp.carstoowner.controller.Owner;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.carstoowner.service.OwnerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public OwnerDto getOwner(@PathVariable Long id) {
        return new OwnerDto(ownerService.findOwner(id));
    }

    @GetMapping("/")
    public List<OwnerDto> getOwners() {
        return ownerService.findAllOwners().stream()
                .map(OwnerDto::new)
                .toList();
    }

    @PostMapping("/")
    public OwnerDto createOwner(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
        return new OwnerDto(ownerService.addOwner(firstName, lastName));
    }

    @PatchMapping("/{id}")
    public OwnerDto updateOwner(@RequestBody @Valid OwnerDto ownerDto) {
        return ownerService.updateOwner(ownerDto);
    }

    @DeleteMapping("/{id}")
    public OwnerDto deleteOwner(@PathVariable Long id) {
        return new OwnerDto(ownerService.deleteOwner(id));
    }
}