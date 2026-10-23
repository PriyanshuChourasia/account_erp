package com.codymitra.shared_service.modules.designation.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DesignationDTO(
        UUID id,
        String name,
        String code,
        UUID parentId,
        String description,
        Boolean active
) {
}
