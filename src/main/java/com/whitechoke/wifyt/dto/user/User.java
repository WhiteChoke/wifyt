package com.whitechoke.wifyt.dto.user;

import jakarta.validation.constraints.NotNull;

public interface User {
    String username();
    String phoneNumber();
    String email();
    String password();
}
