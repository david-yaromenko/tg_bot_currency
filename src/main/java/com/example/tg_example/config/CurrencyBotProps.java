package com.example.tg_example.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(CurrencyBotProps.class)
@ConfigurationProperties(prefix = "currency-bot")
@Getter
@Setter
public class CurrencyBotProps {
    private String token;
}
