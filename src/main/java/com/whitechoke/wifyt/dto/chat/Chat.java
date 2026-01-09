package com.whitechoke.wifyt.dto.chat;

import com.whitechoke.wifyt.enums.ChatType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.Instant;
import java.util.List;

public record Chat (
        @Null
        Long id,
        @NotNull
        String name,
        @Null
        Instant createdAt,
        @NotNull
        ChatType chatType,
        List<Long> participantList
) {
}
