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
                applicationFeatureEntity.getApiMethod(),
                applicationFeatureEntity.getApiDeviceType(),
                applicationFeatureEntity.getEndPoint(),
                applicationFeatureEntity.getDescription(),
                applicationFeatureEntity.getActive()
        );
    }

    /// normalizes code: uppercase, spaces joined with underscore e.g. "account Ledger" -> ACCOUNT_LEDGER
    public static String normalizeCode(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        return code.trim().replaceAll("[\\s-]+", "_").toUpperCase();
    }

    /// derives the code from the name when not supplied, e.g. "Account Ledger" -> ACCOUNT_LEDGER
    public static String resolveCode(String code, String name) {
        if (code != null && !code.isBlank()) {
            return normalizeCode(code);
        }
        return normalizeCode(name);
    }

    public static ApplicationFeatureEntity applicationFeatureEntity(CreateApplicationFeatureDTO applicationFeatureDTO){
        ApplicationFeatureEntity applicationFeature = new ApplicationFeatureEntity();
        applicationFeature.setName(applicationFeatureDTO.name());
        applicationFeature.setCode(resolveCode(null, applicationFeatureDTO.name()));
        applicationFeature.setApiMethod(applicationFeatureDTO.apiMethod());
        applicationFeature.setApiDeviceType(applicationFeatureDTO.apiDeviceType());
        applicationFeature.setEndPoint(applicationFeatureDTO.endPoint());
        applicationFeature.setDescription(applicationFeatureDTO.description());
        applicationFeature.setActive(applicationFeatureDTO.active());
        return applicationFeature;
    }

    public static ApplicationFeatureEntity applicationFeatureEntity(ApplicationFeatureEntity applicationFeature, CreateApplicationFeatureDTO applicationFeatureDTO){
        applicationFeature.setName(applicationFeatureDTO.name());
        applicationFeature.setCode(resolveCode(null, applicationFeatureDTO.name()));
        applicationFeature.setApiMethod(applicationFeatureDTO.apiMethod());
        applicationFeature.setApiDeviceType(applicationFeatureDTO.apiDeviceType());
        applicationFeature.setEndPoint(applicationFeatureDTO.endPoint());
        applicationFeature.setDescription(applicationFeatureDTO.description());
        applicationFeature.setActive(applicationFeatureDTO.active());
        return applicationFeature;
    }
}
