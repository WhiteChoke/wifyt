package com.whitechoke.wifyt.dto;

import com.whitechoke.wifyt.enums.UserRoles;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;


public record Participant(
        @Null
        Long id,
        @NotNull
        Long userId,
        @NotNull
        UserRoles role,
        @NotNull
        Long chatId
) {
}
