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
    public ApplicationFeatureDTO show(String id){
        return ApplicationFeatureMapper.applicationFeatureDTO(findById(id));
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
        String resolvedCode = ApplicationFeatureMapper.resolveCode(null, createApplicationFeatureDTO.name());
        if(applicationFeatureRepository.existsByCode(resolvedCode)){
            throw new DataAlreadyExistsException("Application Feature already exists with this code");
        }
        applicationFeatureRepository.save(ApplicationFeatureMapper.applicationFeatureEntity(createApplicationFeatureDTO));
        return "Application Feature created successfully";
    }

    @Override
    public ApplicationFeatureDTO update(String id, CreateApplicationFeatureDTO createApplicationFeatureDTO){
        findById(id);
        validateUnique(createApplicationFeatureDTO.name(), id);
        ApplicationFeatureEntity updated = applicationFeatureRepository.save(
                ApplicationFeatureMapper.applicationFeatureEntity(findById(id), createApplicationFeatureDTO)
        );
        return ApplicationFeatureMapper.applicationFeatureDTO(updated);
    }

    private void validateUnique(String name, String id) {
        if (applicationFeatureRepository.existsByNameAndIdNot(name, UUID.fromString(id))) {
            throw new DataAlreadyExistsException("Application Feature already exists with this name");
        }
        String resolvedCode = ApplicationFeatureMapper.resolveCode(null, name);
        if (applicationFeatureRepository.existsByCodeAndIdNot(resolvedCode, UUID.fromString(id))) {
            throw new DataAlreadyExistsException("Application Feature already exists with this code");
        }
    }

    private ApplicationFeatureEntity findById(String id) {
        return applicationFeatureRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new DataNotFoundException("Application Feature does not exists")
        );
    }
}
