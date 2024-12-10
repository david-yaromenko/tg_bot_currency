package com.example.tg_example.service;

import com.example.tg_example.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

// ещё один пример сервиса
@Service
@RequiredArgsConstructor
public class NoAnswerService {
    private final MessageSender messageSender;
    private final CurrencyService currencyService;
    private final LogService logService;

    public void sendNoKnow(Update update) {

        var message = MessageDto.builder()
                .chatId(update.getMessage().getChatId())
                .text(String.format("Поки, що я не все розумію, але вчусь\uD83D\uDDA4"))
                .replyTo(update.getMessage().getMessageId())
                .build();

        //log-------------------
        String user_username = update.getMessage().getChat().getUserName();
        long user_id = update.getMessage().getChat().getId();
        String message_text = update.getMessage().getText();
        String answer = message.text();
        //----------------------

        messageSender.sendMessage(message);
        logService.log(user_username, Long.toString(user_id), message_text, answer);
    }
}
