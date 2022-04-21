package ru.ulstu.is.sbapp.car.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.car.model.STO;
import ru.ulstu.is.sbapp.car.service.STOService;

import java.util.List;

@RestController
@RequestMapping("/sto")
public class STOController {
    private final STOService stoService;

    public STOController(STOService stoService) {
        this.stoService = stoService;
    }

    @GetMapping("/{id}")
    public STO getSTO(@PathVariable Long id){
        return stoService.findSTO(id);
    }
    @GetMapping("/")
    public List<STO> getSTOs(){
        return stoService.findAllSTOs();
    }

    @PostMapping("/")
    public STO createSTO(@RequestParam("name") String name) {
        return stoService.addSTO(name);
    }

    @PatchMapping("/{id}")
    public STO updateSTO(@PathVariable Long id,
                         @RequestParam("name") String name){
        return stoService.updateSTO(id, name);
    }

    @DeleteMapping("/{id}")
    public STO deleteSTO(@PathVariable Long id) {
        return stoService.deleteSTO(id);
    }
}
