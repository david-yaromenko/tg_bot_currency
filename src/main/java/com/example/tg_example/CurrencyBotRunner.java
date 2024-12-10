package com.example.tg_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CurrencyBotRunner {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyBotRunner.class, args);

    }
}
