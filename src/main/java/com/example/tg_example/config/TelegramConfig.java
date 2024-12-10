package com.example.tg_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class TelegramConfig {

    @Bean //клиент который будет отправлять ответ пользователю
    public TelegramClient telegramClient(CurrencyBotProps props) {
        return new OkHttpTelegramClient(props.getToken());
    }

    // тут создается бот, который слушает сообщения ему от людей
    // consumer это типа контроллера, в который будут приходить сообщения и в котором будет запускаться
    // вся наша логика
    @Bean
    public TelegramBotsLongPollingApplication telegramBotApplication(
            CurrencyBotProps props, LongPollingUpdateConsumer consumer) throws TelegramApiException {

        var bot = new TelegramBotsLongPollingApplication();
        bot.registerBot(props.getToken(), consumer);
        return bot;
    }
}
