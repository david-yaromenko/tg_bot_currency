package com.example.tg_example.controller;

import com.example.tg_example.service.HelloUserService;
import com.example.tg_example.service.CurrencyService;
import com.example.tg_example.service.MessageSender;
import com.example.tg_example.service.NoAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// это по сути контроллер, в который будут валиться все сообщения
// тут должна быть ТОЛЬКО логика того как понять что от тебя хочет пользователь
// типа если такая команда, то запускаем это, иначе - то
@Service
@RequiredArgsConstructor
public class CurrencyBotController implements LongPollingSingleThreadUpdateConsumer {

    private final CurrencyService currencyService;
    private final HelloUserService helloUserService;
    private final NoAnswerService noAnswerService;

    @Override
    public void consume(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                var text = update.getMessage().getText();

                if (text.equalsIgnoreCase("/start")) {
                    helloUserService.sayHello(update);
                } else if (text.equalsIgnoreCase("Долар США")) {
                    currencyService.getElement(update, text);
                } else if (text.equalsIgnoreCase("Євро")) {
                    currencyService.getElement(update, text);
                } else if (text.equalsIgnoreCase("Злотий")) {
                    currencyService.getElement(update, text);
                } else if (text.equalsIgnoreCase("Канадський долар")) {
                    currencyService.getElement(update, text);
                } else {
                    noAnswerService.sendNoKnow(update);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
