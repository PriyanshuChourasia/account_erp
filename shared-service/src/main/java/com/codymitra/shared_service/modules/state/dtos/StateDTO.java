package com.codymitra.shared_service.modules.state.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StateDTO(
        Long id,
        String name,
        String code,
        String gstCode,
        String description,
        Boolean active
) {}
