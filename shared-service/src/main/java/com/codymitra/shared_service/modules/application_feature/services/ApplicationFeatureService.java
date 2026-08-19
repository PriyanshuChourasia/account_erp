package com.codymitra.shared_service.modules.application_feature.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.application_feature.dtos.ApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.dtos.CreateApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.entities.ApplicationFeatureEntity;

import java.util.List;

public interface ApplicationFeatureService {

    List<ApplicationFeatureDTO> getAllApplicationFeatures();

    String create(CreateApplicationFeatureDTO createApplicationFeatureDTO);

    ApplicationFeatureEntity show(UUID id);
}
