package com.codymitra.shared_service.modules.unique_quantity_code.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UniqueQuantityCodeDTO(
        Long id,
        String name,
        String code,
        String alias,
        String description
) {
}
