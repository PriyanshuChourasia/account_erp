package com.codymitra.shared_service.modules.user_type.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserTypeDTO(
        UUID id,
        String name,
        String code,
        String description,
        Boolean active
) {
}
