package com.codymitra.auth_service.services;

public interface JwtService {

    String generateAccessToken(String username);
    String generateRefreshToken(String username);
    String extractUsername(String token);
    Boolean isTokenValid(String token,String username);
}
