package com.codymitra.shared_service.modules.user.dtos;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record UpdateUserDTO(
        String name,

        @Email(message = "Invalid email")
        String email,

        Long contactNo,

        Long altContactNo,

        String licenseNo,

        String telePhoneNo,

        String faxNo,

        String website,

        String password,

        LocalDate dateOfBirth,

        Boolean active
) {
}
