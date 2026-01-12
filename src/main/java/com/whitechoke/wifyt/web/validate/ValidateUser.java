package com.whitechoke.wifyt.web.validate;

import com.whitechoke.wifyt.dto.user.User;
import org.springframework.stereotype.Component;

@Component
public class ValidateUser {

    public <T extends User> void validateUser(T user) {
        if (user.email() == null) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (user.username() == null) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (user.password() == null) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

    }
}
