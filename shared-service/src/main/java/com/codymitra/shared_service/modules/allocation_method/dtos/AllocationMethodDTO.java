package com.codymitra.shared_service.modules.allocation_method.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AllocationMethodDTO(
        UUID id,
        String name,
        String code,
        String description,
        Boolean active
) {}
