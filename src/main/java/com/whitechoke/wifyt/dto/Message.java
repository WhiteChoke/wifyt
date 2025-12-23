package com.whitechoke.wifyt.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.Instant;
import java.util.UUID;

public record Message(
        @Null
        UUID id,
        @NotNull
        UUID senderId,
        @NotNull
        UUID chatId,
        @NotNull
        String message,
        @Null
        Instant createdAt
) {
}
