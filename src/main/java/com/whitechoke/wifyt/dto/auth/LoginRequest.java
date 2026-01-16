package com.whitechoke.wifyt.dto.auth;

public record LoginRequest(
        String username,
        String password
) {
}
