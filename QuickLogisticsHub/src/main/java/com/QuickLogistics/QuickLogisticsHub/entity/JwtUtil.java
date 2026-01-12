package com.QuickLogistics.QuickLogisticsHub.entity;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY  = "MyVerySecureSecretKeyForJWTTokenGenerationAndValidation1234567890";

    private static final long EXPIRATION_TIME = 1000*60*2;
    
    public String generateToken(Users user) {
        
        return Jwts.builder()
            .subject(user.getUsername())
            .claim("roles", user.getRole())
            .issuedAt(new Date())
            .expiration(
                new Date(System.currentTimeMillis() + EXPIRATION_TIME)
            )
            .signWith(
                Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
            .compact();

    }
}