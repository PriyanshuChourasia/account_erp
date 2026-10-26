package com.codymitra.shared_service.modules.gender.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GenderDTO(
        UUID id,
        String name,
        String code,
        String description,
        Boolean status
) {
}
