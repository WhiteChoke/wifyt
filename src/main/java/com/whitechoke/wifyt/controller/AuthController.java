package com.whitechoke.wifyt.controller;

import com.whitechoke.wifyt.dto.auth.AuthResponse;
import com.whitechoke.wifyt.dto.auth.LoginRequest;
import com.whitechoke.wifyt.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        var userDetails = service.authenticate(
                request.username(),
                request.password()
        );

        var token = service.generateToken(userDetails);

        AuthResponse response = new AuthResponse(
                token,
                86400
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
