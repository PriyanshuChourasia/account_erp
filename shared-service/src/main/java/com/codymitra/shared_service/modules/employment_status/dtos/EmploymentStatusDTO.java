package com.codymitra.shared_service.modules.employment_status.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmploymentStatusDTO(
        UUID id,
        String name,
        String code,
        String description,
        Boolean active
) {
}
