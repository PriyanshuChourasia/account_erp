package com.codymitra.shared_service.modules.designation.services;

import com.codymitra.shared_service.modules.designation.dtos.CreateDesignationRequest;
import com.codymitra.shared_service.modules.designation.dtos.DesignationDTO;
import com.codymitra.shared_service.modules.designation.dtos.DesignationHierarchyDTO;

import java.util.List;
import java.util.UUID;

public interface DesignationService {

    List<DesignationDTO> getAll();

    List<DesignationHierarchyDTO> getAllWithChildren();

    DesignationDTO getById(UUID id);

    DesignationDTO create(CreateDesignationRequest request);

    DesignationDTO update(UUID id, CreateDesignationRequest request);

    String delete(UUID id);
}
