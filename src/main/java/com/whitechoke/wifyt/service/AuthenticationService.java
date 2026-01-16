package com.whitechoke.wifyt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    UserDetails authenticate(String username, String password);
    String generateToken(UserDetails userDetails);
}
