package com.codymitra.shared_service.modules.unique_quantity_code.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UniqueQuantityCodeDTO(
        UUID id,
        String name,
        String code,
        String alias,
        String description
) {
}
