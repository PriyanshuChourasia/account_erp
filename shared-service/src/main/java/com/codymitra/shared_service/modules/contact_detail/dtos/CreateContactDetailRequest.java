package com.codymitra.shared_service.modules.contact_detail.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateContactDetailRequest(
        @NotNull(message = "Employee id is required")
        UUID employeeId,

        String mobile,

        String altMobile,

        @Email(message = "Invalid email. Please check again")
        String email,

        @Email(message = "Invalid email. Please check again")
        String personalEmail,

        String address,

        String emergencyContactName,

        String emergencyContactNumber
) {
}