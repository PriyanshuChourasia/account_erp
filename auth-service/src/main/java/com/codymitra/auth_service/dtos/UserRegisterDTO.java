package com.codymitra.auth_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

        @NotBlank(message = "Country Code is required")
        @Size(max = 3,message = "Country code can be of maximum 3 character")
        String countryCode,

        Long altContactNo,

        LocalDate dateOfBirth
) {
}
