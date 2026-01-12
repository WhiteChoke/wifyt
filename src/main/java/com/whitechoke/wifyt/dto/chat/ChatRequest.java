package com.whitechoke.wifyt.dto.chat;

import com.whitechoke.wifyt.enums.ChatType;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ChatRequest (
        @NotNull
        String name,
        @NotNull
        ChatType chatType,
        List<Long> participantList
) implements ChatInterface{
}
