package com.codymitra.shared_service.modules.account_nature.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountNatureDTO(
        UUID id,
        String name,
        Integer code,
        String description
) {}
