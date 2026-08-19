package com.codymitra.shared_service.modules.state.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StateDTO(
        UUID id,
        String name,
        String code,
        String gstCode,
        String description,
        Boolean active
) {}
