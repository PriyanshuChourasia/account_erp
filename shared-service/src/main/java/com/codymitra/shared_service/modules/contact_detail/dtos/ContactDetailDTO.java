package com.codymitra.shared_service.modules.contact_detail.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContactDetailDTO(
        UUID id,
        UUID employeeId,
        String employeeCode,
        String personalEmail,
        String address,
        String emergencyContactName,
        String emergencyContactNumber,
        Boolean active
) {
}