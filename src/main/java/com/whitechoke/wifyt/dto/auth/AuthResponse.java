package com.whitechoke.wifyt.dto.auth;

public record AuthResponse(
        String token,
        long expiresIn
) {
}
