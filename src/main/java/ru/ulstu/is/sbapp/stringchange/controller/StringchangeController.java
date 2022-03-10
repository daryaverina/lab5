package ru.ulstu.is.sbapp.stringchange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.stringchange.service.StringchangeService;

@RestController
public class StringchangeController {
    private final StringchangeService stringchangeService;

    public StringchangeController(StringchangeService stringchangeService) {
        this.stringchangeService = stringchangeService;
    }

    @GetMapping("/")
    public String changer(@RequestParam(value = "word", defaultValue = "улгту") String word,
                          @RequestParam(value = "choice", defaultValue = "first") String choice) {
        return stringchangeService.change(word, choice);
    }
}
