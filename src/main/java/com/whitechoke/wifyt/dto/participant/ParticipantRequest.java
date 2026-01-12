package com.whitechoke.wifyt.dto.participant;

import com.whitechoke.wifyt.enums.UserRoles;
import jakarta.validation.constraints.NotNull;

public record ParticipantRequest (
        @NotNull
        Long userId,
        @NotNull
        UserRoles role,
        @NotNull
        Long chatId
) implements ParticipantInterface{
}
