package com.whitechoke.wifyt.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.Instant;
import java.util.UUID;

public record User(
        @Null
        UUID id,
        @NotNull
        String username,
        String phoneNumber,
        @NotNull
        String email,
        @NotNull
        String password,
        @Null
        Instant createdAt
) {
}
