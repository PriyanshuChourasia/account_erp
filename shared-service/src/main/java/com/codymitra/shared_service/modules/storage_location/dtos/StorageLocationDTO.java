package com.codymitra.shared_service.modules.storage_location.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageLocationDTO(
        UUID id,
        String name,
        String code,
        String alias,
        UUID parentId,
        String description,
        Boolean isActive
) {
}
