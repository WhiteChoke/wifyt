package com.whitechoke.wifyt.dto;

import java.util.UUID;

public record User(
        UUID id,
        String username,
        String phoneNumber,
        String email,
        String password
) {
}
