package ru.ulstu.is.sbapp.carstoowner.controller.STO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.carstoowner.service.STOService;

import javax.validation.Valid;

@Controller
@RequestMapping("/sto")
public class STOMvcController {
    private final STOService stoService;

    public STOMvcController(STOService stoService) {
        this.stoService = stoService;
    }

    @GetMapping
    public String getSTOs(Model model) {
        model.addAttribute("stos",
                stoService.findAllSTOs().stream()
                        .map(STODto::new)
                        .toList());
        return "sto";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editSTO(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("stoDto", new STODto());
        }
        else {
            model.addAttribute("stoId", id);
            model.addAttribute("stoDto", new STODto(stoService.findSTO(id)));
        }
        return "sto-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveSTO(@PathVariable(required = false) Long id,
                          @ModelAttribute @Valid STODto stoDto,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "sto-edit";
        }
        if (id == null || id <= 0) {
            stoService.addSTO(stoDto.getName());
        } else {
            stoService.updateSTO(id, stoDto.getName());
        }
        return "redirect:/sto";
    }

    @PostMapping("/delete/{id}")
    public String deleteSTO(@PathVariable Long id) {
        stoService.deleteSTO(id);
        return "redirect:/sto";
    }
}
