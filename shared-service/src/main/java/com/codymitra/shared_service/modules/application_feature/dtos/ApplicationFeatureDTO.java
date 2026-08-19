package com.codymitra.shared_service.modules.application_feature.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApplicationFeatureDTO(
        UUID id,
        String name,
        Integer code,
        String description
) {}
