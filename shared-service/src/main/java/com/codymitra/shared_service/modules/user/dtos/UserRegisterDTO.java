package com.codymitra.shared_service.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRegisterDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @NotNull(message = "Contact number is required")
        Long contactNo,

        Long altContactNo,

        LocalDate dateOfBirth
) {
}
