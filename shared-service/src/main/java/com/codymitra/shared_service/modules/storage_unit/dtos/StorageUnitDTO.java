package com.codymitra.shared_service.modules.storage_unit.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageUnitDTO(
        UUID id,
        String name,
        Integer code,
        String description
) {}
