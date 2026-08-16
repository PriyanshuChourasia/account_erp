package com.codymitra.shared_service.modules.storage_location.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageLocationHierarchyDTO(
        Long id,
        String name,
        String code,
        String alias,
        Long parentId,
        String description,
        Boolean isActive,
        List<StorageLocationHierarchyDTO> children
) {
}
