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
                applicationModuleEntity.getDescription(),
                applicationModuleEntity.getEndpoint(),
                applicationModuleEntity.getIsSystem(),
                applicationModuleEntity.getVersion(),
                applicationModuleEntity.getActive()
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

    public static ApplicationModuleEntity applicationModuleEntity(CreateApplicationModuleDTO applicationModuleDTO){
        ApplicationModuleEntity applicationModule = new ApplicationModuleEntity();
        applicationModule.setName(applicationModuleDTO.name());
        applicationModule.setCode(resolveCode(null, applicationModuleDTO.name()));
        applicationModule.setDescription(applicationModuleDTO.description());
        applicationModule.setEndpoint(applicationModuleDTO.endpoint());
        applicationModule.setIsSystem(applicationModuleDTO.isSystem());
        applicationModule.setActive(true);
        return applicationModule;
    }

    public static ApplicationModuleEntity applicationModuleEntity(ApplicationModuleEntity applicationModule, CreateApplicationModuleDTO applicationModuleDTO){
        applicationModule.setName(applicationModuleDTO.name());
        applicationModule.setCode(resolveCode(null, applicationModuleDTO.name()));
        applicationModule.setDescription(applicationModuleDTO.description());
        applicationModule.setEndpoint(applicationModuleDTO.endpoint());
        applicationModule.setIsSystem(applicationModuleDTO.isSystem());
        applicationModule.setActive(true);
        return applicationModule;
    }
}
