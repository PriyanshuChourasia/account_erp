package com.codymitra.shared_service.modules.application_module.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.application_module.dtos.ApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.dtos.CreateApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.entities.ApplicationModuleEntity;
import com.codymitra.shared_service.modules.application_module.mappers.ApplicationModuleMapper;
import com.codymitra.shared_service.modules.application_module.repositories.ApplicationModuleRepository;
import com.codymitra.shared_service.modules.application_module.services.ApplicationModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationModuleServiceImpl implements ApplicationModuleService {

    private final ApplicationModuleRepository applicationModuleRepository;



    @Override
    public ApplicationModuleEntity show(UUID id){
        return applicationModuleRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Application Module does not exists")
        );
    }

    @Override
    public List<ApplicationModuleDTO> getAllApplicationModules(){
        List<ApplicationModuleEntity> applicationModuleEntities = applicationModuleRepository.findAll();
        return applicationModuleEntities.stream().map(ApplicationModuleMapper::applicationModuleDTO).toList();
    }

    @Override
    public String create(CreateApplicationModuleDTO createApplicationModuleDTO){
        if(applicationModuleRepository.existsByName(createApplicationModuleDTO.name())){
            throw new DataAlreadyExistsException("Application Module already exists");
        }
        applicationModuleRepository.save(ApplicationModuleMapper.applicationModuleEntity(createApplicationModuleDTO));
        return "Application Module created successfully";
    }
}
