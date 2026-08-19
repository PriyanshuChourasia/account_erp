package com.codymitra.shared_service.modules.application_feature.mappers;

import com.codymitra.shared_service.modules.application_feature.dtos.ApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.dtos.CreateApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.entities.ApplicationFeatureEntity;

public final class ApplicationFeatureMapper {

    public static ApplicationFeatureDTO applicationFeatureDTO(ApplicationFeatureEntity applicationFeatureEntity){
        return new ApplicationFeatureDTO(
                applicationFeatureEntity.getId(),
                applicationFeatureEntity.getName(),
                applicationFeatureEntity.getCode(),
                applicationFeatureEntity.getDescription()
        );
    }

    public static ApplicationFeatureEntity applicationFeatureEntity(CreateApplicationFeatureDTO applicationFeatureDTO){
        ApplicationFeatureEntity applicationFeature = new ApplicationFeatureEntity();
        applicationFeature.setName(applicationFeatureDTO.name());
        applicationFeature.setCode(applicationFeatureDTO.code());
        applicationFeature.setDescription(applicationFeatureDTO.description());
        return applicationFeature;
    }
}
