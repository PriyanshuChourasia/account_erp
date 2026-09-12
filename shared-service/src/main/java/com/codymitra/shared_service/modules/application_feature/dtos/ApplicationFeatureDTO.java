package com.codymitra.shared_service.modules.application_feature.dtos;

import java.util.UUID;
import com.codymitra.shared_service.enums.APIDeviceType;
import com.codymitra.shared_service.enums.ApiMethod;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApplicationFeatureDTO(
        UUID id,
        String name,
        String code,
        ApiMethod apiMethod,
        APIDeviceType apiDeviceType,
        String endPoint,
        String description,
        Boolean active
) {}
