package com.example.tg_example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LogService {

    private static final Logger logger = LogManager.getLogger();
    public void log(String user_username, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        logger.info("Message from " + user_username + ". (id = " + user_id + ") \n Text - " +
                txt + "\n Bot answer: \n Text - " + bot_answer);
    }

}
