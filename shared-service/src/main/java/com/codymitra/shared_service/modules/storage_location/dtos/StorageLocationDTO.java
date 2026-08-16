package com.codymitra.shared_service.modules.storage_location.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageLocationDTO(
        Long id,
        String name,
        String code,
        String alias,
        Long parentId,
        String description,
        Boolean isActive
) {
}
