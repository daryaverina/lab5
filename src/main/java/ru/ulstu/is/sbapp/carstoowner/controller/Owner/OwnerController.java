package ru.ulstu.is.sbapp.carstoowner.controller.Owner;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.WebConfiguration;
import ru.ulstu.is.sbapp.carstoowner.service.OwnerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/owner")
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
    public OwnerDto createOwner(@RequestBody @Valid OwnerDto ownerDto) {
        return new OwnerDto(ownerService.addOwner(ownerDto.getFirstName(), ownerDto.getLastName()));
    }

    @PatchMapping("/{id}")
    public OwnerDto updateOwner(@PathVariable Long id, @RequestBody @Valid OwnerDto ownerDto) {
        return new OwnerDto(ownerService.updateOwner(id, ownerDto.getFirstName(), ownerDto.getLastName()));
    }

    @DeleteMapping("/{id}")
    public OwnerDto deleteOwner(@PathVariable Long id) {
        return new OwnerDto(ownerService.deleteOwner(id));
    }
}