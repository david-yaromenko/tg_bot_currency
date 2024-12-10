package com.example.tg_example.service;

import com.example.tg_example.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// этот сервис нужен для отправки сообщений в телеграм
// он принимает "наш" объект MessageDto, в котором написано куда и какое сообщение нужно отправить
// переделывает это в понятную для телеги форму и отправляет. И заоодно обрабатывает возможные ошибки
@Service
@RequiredArgsConstructor
public class MessageSender {
    private final TelegramClient telegramClient;

    public void sendMessage(MessageDto message) {
        var sendMessage = new SendMessage(Long.toString(message.chatId()), message.text());
        sendMessage.setReplyToMessageId(message.replyTo());

        var keyboardRows = Arrays.asList(
                new KeyboardRow("Долар США", "Євро"),
                new KeyboardRow("Злотий", "Канадський долар"));

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(keyboardRows);
        keyboard.setIsPersistent(true);
        keyboard.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(keyboard);

        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Bot can't send message");
        }
    }
}
