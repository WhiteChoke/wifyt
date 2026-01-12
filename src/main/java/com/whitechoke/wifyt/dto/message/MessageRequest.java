package com.whitechoke.wifyt.dto.message;

import jakarta.validation.constraints.NotNull;

public record MessageRequest(
        @NotNull
        Long senderId,
        @NotNull
        Long chatId,
        @NotNull
        String message
) implements MessageInterface{
}
