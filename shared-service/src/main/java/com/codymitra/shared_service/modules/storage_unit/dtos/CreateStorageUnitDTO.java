package com.codymitra.shared_service.modules.storage_unit.dtos;


public record CreateStorageUnitDTO(
        String name,
        Integer code,
        String description
) {}
