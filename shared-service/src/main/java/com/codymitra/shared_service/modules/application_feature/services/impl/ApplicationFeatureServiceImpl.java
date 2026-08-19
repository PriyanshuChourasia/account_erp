package com.codymitra.shared_service.modules.application_feature.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.application_feature.dtos.ApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.dtos.CreateApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.entities.ApplicationFeatureEntity;
import com.codymitra.shared_service.modules.application_feature.mappers.ApplicationFeatureMapper;
import com.codymitra.shared_service.modules.application_feature.repositories.ApplicationFeatureRepository;
import com.codymitra.shared_service.modules.application_feature.services.ApplicationFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationFeatureServiceImpl implements ApplicationFeatureService {

    private final ApplicationFeatureRepository applicationFeatureRepository;



    @Override
    public ApplicationFeatureEntity show(UUID id){
        return applicationFeatureRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Application Feature does not exists")
        );
    }

    @Override
    public List<ApplicationFeatureDTO> getAllApplicationFeatures(){
        List<ApplicationFeatureEntity> applicationFeatureEntities = applicationFeatureRepository.findAll();
        return applicationFeatureEntities.stream().map(ApplicationFeatureMapper::applicationFeatureDTO).toList();
    }

    @Override
    public String create(CreateApplicationFeatureDTO createApplicationFeatureDTO){
        if(applicationFeatureRepository.existsByName(createApplicationFeatureDTO.name())){
            throw new DataAlreadyExistsException("Application Feature already exists");
        }
        applicationFeatureRepository.save(ApplicationFeatureMapper.applicationFeatureEntity(createApplicationFeatureDTO));
        return "Application Feature created successfully";
    }
}
