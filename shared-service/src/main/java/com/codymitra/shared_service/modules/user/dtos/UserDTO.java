package com.codymitra.shared_service.modules.user.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(
        UUID id,
        String name,
        String username,
        String email,
        Long contactNo,
        Long altContactNo,
        String licenseNo,
        String telePhoneNo,
        String faxNo,
        String website,
        String code,
        LocalDate dateOfBirth,
        Boolean active
) {
}
