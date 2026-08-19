package com.codymitra.auth_service.dtos;

public record AuthResponseDTO(
        String accessToken,
        String refreshToken
) {
}
