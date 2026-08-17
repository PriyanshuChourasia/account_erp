package com.codymitra.auth_service.services.impl;

import com.codymitra.auth_service.configs.JwtConfig;
import com.codymitra.auth_service.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtConfig jwtConfig;


    @Override
    public String generateAccessToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(username,claims,Long.valueOf(jwtConfig.accessExpiration()));
    }

    @Override
    public String generateRefreshToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(username,claims,Long.valueOf(jwtConfig.refreshExpiration()));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    @Override
    public Boolean isTokenValid(String token, String expectedUsername) {
        final String username = extractUsername(token);
        return (username.equals(expectedUsername)) && !isTokenExpired(token);
    }


    private String createToken(String username, Map<String,Object> claims, Long expirationSeconds){
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ expirationSeconds))
                .signWith(getSecretKey())
                .compact();
    }


    private SecretKey getSecretKey(){
        byte[] keyBytes = jwtConfig.secret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaim(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaim(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date extractExpirationDate(String token){
        return extractClaims(token,Claims::getExpiration);
    }


    private boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }
}
