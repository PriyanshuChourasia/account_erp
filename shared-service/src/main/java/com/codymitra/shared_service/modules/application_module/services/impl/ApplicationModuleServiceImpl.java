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
        return findById(id);
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
        String resolvedCode = ApplicationModuleMapper.resolveCode(null, createApplicationModuleDTO.name());
        if(applicationModuleRepository.existsByCode(resolvedCode)){
            throw new DataAlreadyExistsException("Application Module already exists with this code");
        }
        ApplicationModuleEntity applicationModule = ApplicationModuleMapper.applicationModuleEntity(createApplicationModuleDTO);
        applicationModule.setVersion(nextVersion());
        applicationModuleRepository.save(applicationModule);
        return "Application Module created successfully";
    }

    @Override
    public ApplicationModuleDTO update(UUID id, CreateApplicationModuleDTO createApplicationModuleDTO){
        ApplicationModuleEntity existing = findById(id);
        validateUnique(createApplicationModuleDTO.name(), id);
        ApplicationModuleEntity updated = ApplicationModuleMapper.applicationModuleEntity(existing, createApplicationModuleDTO);
        applicationModuleRepository.save(updated);
        return ApplicationModuleMapper.applicationModuleDTO(updated);
    }

    private Integer nextVersion(){
        return applicationModuleRepository.findTopByOrderByVersionDesc()
                .map(applicationModule -> applicationModule.getVersion() + 1)
                .orElse(1);
    }

    private void validateUnique(String name, UUID id) {
        if (applicationModuleRepository.existsByNameAndIdNot(name, id)) {
            throw new DataAlreadyExistsException("Application Module already exists with this name");
        }
        String resolvedCode = ApplicationModuleMapper.resolveCode(null, name);
        if (applicationModuleRepository.existsByCodeAndIdNot(resolvedCode, id)) {
            throw new DataAlreadyExistsException("Application Module already exists with this code");
        }
    }

    private ApplicationModuleEntity findById(UUID id) {
        return applicationModuleRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Application Module does not exists")
        );
    }
}
