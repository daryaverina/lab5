package ru.ulstu.is.sbapp.stringchange.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ulstu.is.sbapp.stringchange.domain.AllLetters;
import ru.ulstu.is.sbapp.stringchange.domain.FirstLetter;
import ru.ulstu.is.sbapp.stringchange.domain.LastLetter;

@Configuration
public class StringchangeConfiguration {
    private final Logger log = LoggerFactory.getLogger(StringchangeConfiguration.class);

    @Bean(value = "first")
    public FirstLetter createFirstLetterChanger()
    {
        log.info("Call createFirstLetterChanger()");
        return new FirstLetter();
    }

    @Bean(value = "last")
    public LastLetter createLastLetterChanger()
    {
        log.info("Call createLastLetterChanger()");
        return new LastLetter();
    }

    @Bean(value = "all")
    public AllLetters createAllLettersChanger()
    {
        log.info("Call createAllLettersChanger()");
        return new AllLetters();
    }
}
