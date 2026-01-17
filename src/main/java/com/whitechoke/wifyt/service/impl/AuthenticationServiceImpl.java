package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.auth.AuthResponse;
import com.whitechoke.wifyt.dto.user.UserRequest;
import com.whitechoke.wifyt.service.AuthenticationService;
import com.whitechoke.wifyt.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiry-ms}")
    private Long jwtExpiryMs;

    @Override
    public AuthResponse register(UserRequest request) {

        var createdUser = userService.createUser(request);

        if (createdUser != null) {
            String token = generateToken(
                    userDetailsService.loadUserByUsername(createdUser.username())
            );

            return new AuthResponse(
                    token,
                    jwtExpiryMs / 1000
            );
        }

        return null;
    }

    @Override
    public AuthResponse login(String username, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = generateToken(
                userDetailsService.loadUserByUsername(username)
        );

        return new AuthResponse(
                token,
                jwtExpiryMs / 1000
        );
    }

    @Override
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        var claims = extractClaims(token);

        return
                claims.getSubject().equals(userDetails.getUsername())
                && !claims.getExpiration().before(new Date());
    }

    @Override
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
