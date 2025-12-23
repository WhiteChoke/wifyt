package com.whitechoke.wifyt.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.Instant;

public record Message(
        @Null
        Long id,
        @NotNull
        Long senderId,
        @NotNull
        Long chatId,
        @NotNull
        String message,
        @Null
        Instant createdAt
) {
}
