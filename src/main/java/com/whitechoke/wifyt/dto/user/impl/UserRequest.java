package com.whitechoke.wifyt.dto.user.impl;

import com.whitechoke.wifyt.dto.user.User;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull
        String username,
        String phoneNumber,
        @NotNull
        String email,
        @NotNull
        String password
) implements User {
}
