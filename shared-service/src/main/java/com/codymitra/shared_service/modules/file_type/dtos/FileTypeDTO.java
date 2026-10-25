package com.codymitra.shared_service.modules.file_type.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FileTypeDTO(
        UUID id,
        String name,
        String code,
        String description,
        Boolean active
) {
}