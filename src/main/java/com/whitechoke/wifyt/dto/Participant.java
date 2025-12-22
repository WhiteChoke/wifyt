package com.whitechoke.wifyt.dto;

import com.whitechoke.wifyt.enums.UserRoles;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.UUID;

public record Participant(
        @Null
        UUID id,
        @NotNull
        UUID userId,
        @NotNull
        UserRoles role,
        @NotNull
        UUID chatId
) {
}
