package com.codymitra.auth_service.dtos;

import java.util.UUID;

public record ProfileResponseDTO(
        UUID id,
        String name,
        String email,
        String username,
        Long contactNo
){}
