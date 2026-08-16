package com.codymitra.shared_service.modules.storage_unit.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageUnitDTO(
        Long id,
        String name,
        Integer code,
        String description
) {}
