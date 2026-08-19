package com.codymitra.shared_service.modules.application_module.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApplicationModuleDTO(
        UUID id,
        String name,
        Integer code,
        String description
) {}
