package com.codymitra.shared_service.modules.application_feature.dtos;

import com.codymitra.shared_service.enums.APIDeviceType;
import com.codymitra.shared_service.enums.ApiMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateApplicationFeatureDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "API method is required")
        ApiMethod apiMethod,
        @NotNull(message = "API device type is required")
        APIDeviceType apiDeviceType,
        @NotBlank(message = "End point is required")
        String endPoint,
        String description,
        @NotNull(message = "Active is required")
        Boolean active
) {}
