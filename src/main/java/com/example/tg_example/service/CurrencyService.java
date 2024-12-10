package com.example.tg_example.service;

import com.example.tg_example.dto.MessageDto;
import com.example.tg_example.model.CurrencyModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final MessageSender messageSender;
    private final LogService logService;

    public void getElement(Update update, String text) throws Exception {

        Document html = Jsoup.connect("https://bank.gov.ua/ua/markets/exchangerates").get();
        Elements exchangeRate = html.select("tbody tr");

        List<CurrencyModel> currencyModel = exchangeRate.stream()
                .map((element -> {
                    element.getElementsByAttribute("data-label").text();
                    String name = element.select("a").text();
                    String price = element.select("td:nth-child(5)").text();
                    return new CurrencyModel(name, price);

                })).filter(e -> Objects.equals(e.getName(), text)).toList();

        currencyModel.forEach(e -> {
            var message = MessageDto.builder()
                    .chatId(update.getMessage().getChatId())
                    .text(e.toString() + "\uD83D\uDE80")
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
        });
    }
}
