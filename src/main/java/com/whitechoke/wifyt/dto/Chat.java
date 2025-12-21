package com.whitechoke.wifyt.dto;

import com.whitechoke.wifyt.enums.ChatType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Chat (
        @Null
        UUID id,
        @NotNull
        String name,
        @Null
        Instant createdAt,
        @NotNull
        ChatType chatType,
        List<UUID> participantList
) {
}
