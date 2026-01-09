package com.whitechoke.wifyt.dto.user;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.Instant;

public record User(
        @Null
        Long id,
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
