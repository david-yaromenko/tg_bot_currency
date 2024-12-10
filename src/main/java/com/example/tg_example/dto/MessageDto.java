package com.example.tg_example.dto;

import lombok.Builder;

@Builder
public record MessageDto (
        String text,
        Long chatId,
        Integer replyTo
){
}
