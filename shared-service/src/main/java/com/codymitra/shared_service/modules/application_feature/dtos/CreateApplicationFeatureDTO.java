package com.codymitra.shared_service.modules.application_feature.dtos;


public record CreateApplicationFeatureDTO(
        String name,
        Integer code,
        String description
) {}
