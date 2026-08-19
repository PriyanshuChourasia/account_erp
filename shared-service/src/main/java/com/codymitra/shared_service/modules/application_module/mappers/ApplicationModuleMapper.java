package com.codymitra.shared_service.modules.application_module.mappers;

import com.codymitra.shared_service.modules.application_module.dtos.ApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.dtos.CreateApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.entities.ApplicationModuleEntity;

public final class ApplicationModuleMapper {

    public static ApplicationModuleDTO applicationModuleDTO(ApplicationModuleEntity applicationModuleEntity){
        return new ApplicationModuleDTO(
                applicationModuleEntity.getId(),
                applicationModuleEntity.getName(),
                applicationModuleEntity.getCode(),
                applicationModuleEntity.getDescription()
        );
    }

    public static ApplicationModuleEntity applicationModuleEntity(CreateApplicationModuleDTO applicationModuleDTO){
        ApplicationModuleEntity applicationModule = new ApplicationModuleEntity();
        applicationModule.setName(applicationModuleDTO.name());
        applicationModule.setCode(applicationModuleDTO.code());
        applicationModule.setDescription(applicationModuleDTO.description());
        return applicationModule;
    }
}
