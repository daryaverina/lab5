package ru.ulstu.is.sbapp.stringchange.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.stringchange.domain.Stringchange;

@Service
public class StringchangeService {
    private final ApplicationContext applicationContext;

    public StringchangeService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String change(String word, String choice){
        final Stringchange stringchange = (Stringchange) applicationContext.getBean(choice);
        return String.format("%s = %s!", word, stringchange.change(word));
    }
}
