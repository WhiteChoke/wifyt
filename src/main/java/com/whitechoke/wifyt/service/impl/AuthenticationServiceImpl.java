package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.auth.AuthResponse;
import com.whitechoke.wifyt.dto.user.UserRequest;
import com.whitechoke.wifyt.security.ChatUserDetails;
import com.whitechoke.wifyt.service.AuthenticationService;
import com.whitechoke.wifyt.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiry-ms}")
    private Long jwtExpiryMs;

    @Override
    public AuthResponse register(UserRequest request) {

        var createdUser = userService.createUser(request);

        if (createdUser != null) {
            String token = generateToken(
                    (ChatUserDetails) userDetailsService.loadUserByUsername(createdUser.username())
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

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication.getPrincipal() != null){
            String token = generateToken(
                    (ChatUserDetails) authentication.getPrincipal()
            );

            return new AuthResponse(
                    token,
                    jwtExpiryMs / 1000
            );
        } else {
             logger.error("Got null auth principal");
             return null;
        }
    }

    @Override
    public String generateToken(ChatUserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDetails.getId());

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
