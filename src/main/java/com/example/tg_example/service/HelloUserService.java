package com.example.tg_example.service;

import com.example.tg_example.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class HelloUserService {
    private final MessageSender messageSender;

    public void sayHello(Update update) {

        var userName = update.getMessage().getFrom().getUserName();

        var message = MessageDto.builder()
                .chatId(update.getMessage().getChatId())
                .text("Привіт, " + userName + "!\uD83D\uDC4B" + "\nОбери валюту, щоб дізнатися її курс.")
                .replyTo(update.getMessage().getMessageId())
                .build();

        messageSender.sendMessage(message);
    }
}
