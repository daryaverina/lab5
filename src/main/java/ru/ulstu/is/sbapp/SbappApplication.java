package ru.ulstu.is.sbapp;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbappApplication {
    private final Logger log = LoggerFactory.getLogger(SbappApplication.class);
    private static ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(SbappApplication.class, args);
    }
}
