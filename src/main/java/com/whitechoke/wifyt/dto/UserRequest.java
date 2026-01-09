package com.whitechoke.wifyt.dto;

import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull
        String username,
        String phoneNumber,
        @NotNull
        String email,
        @NotNull
        String password
) {
}
