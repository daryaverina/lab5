package ru.ulstu.is.sbapp.carstoowner.controller.STO;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.WebConfiguration;
import ru.ulstu.is.sbapp.carstoowner.service.STOService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/sto")
public class STOController {
    private final STOService stoService;

    public STOController(STOService stoService) {
        this.stoService = stoService;
    }

    @GetMapping("/{id}")
    public STODto getSTO(@PathVariable Long id){
        return new STODto(stoService.findSTO(id));
    }

    @GetMapping("/")
    public List<STODto> getSTOs(){
        return stoService.findAllSTOs().stream()
                .map(STODto::new)
                .toList();
    }

    @PostMapping("/")
    public STODto createSTO(@RequestParam("name") String name) {
        return new STODto(stoService.addSTO(name));
    }

    @PatchMapping("/{id}")
    public STODto updateSTO(@PathVariable Long id, @RequestBody @Valid STODto stoDto){
        return new STODto(stoService.updateSTO(id, stoDto.getName()));
    }

    @DeleteMapping("/{id}")
    public STODto deleteSTO(@PathVariable Long id) {
        return new STODto(stoService.deleteSTO(id));
    }
}
