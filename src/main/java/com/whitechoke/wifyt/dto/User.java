package com.whitechoke.wifyt.dto;

import java.time.Instant;
import java.util.UUID;

public record User(
        UUID id,
        String username,
        String phoneNumber,
        String email,
        String password,
        Instant createdAt
) {
}
