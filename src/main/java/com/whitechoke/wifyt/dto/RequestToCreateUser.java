package com.whitechoke.wifyt.dto;

import jakarta.validation.constraints.NotNull;

public record RequestToCreateUser (
        @NotNull
        String username,
        String phoneNumber,
        @NotNull
        String email,
        @NotNull
        String password
) {
}
