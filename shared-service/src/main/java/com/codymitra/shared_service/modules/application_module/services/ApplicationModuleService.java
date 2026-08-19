package com.codymitra.shared_service.modules.application_module.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.application_module.dtos.ApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.dtos.CreateApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.entities.ApplicationModuleEntity;

import java.util.List;

public interface ApplicationModuleService {

    List<ApplicationModuleDTO> getAllApplicationModules();

    String create(CreateApplicationModuleDTO createApplicationModuleDTO);

    ApplicationModuleEntity show(UUID id);
}
