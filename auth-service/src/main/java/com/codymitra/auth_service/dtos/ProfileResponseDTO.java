package com.codymitra.auth_service.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ProfileResponseDTO(
        @NotBlank(message = "Id cannot be blank")
        UUID id,
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Contact No cannot be blank")
        Long contactNo
){}
