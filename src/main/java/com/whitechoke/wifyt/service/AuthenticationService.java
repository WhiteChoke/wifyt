package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.auth.AuthResponse;
import com.whitechoke.wifyt.dto.user.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthResponse register(UserRequest request);
    AuthResponse login(String username, String password);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractUsername(String token);
}
