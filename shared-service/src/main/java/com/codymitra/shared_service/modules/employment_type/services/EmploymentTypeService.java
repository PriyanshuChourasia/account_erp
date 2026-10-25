package com.codymitra.shared_service.modules.employment_type.services;

import com.codymitra.shared_service.modules.employment_type.dtos.CreateEmploymentTypeRequest;
import com.codymitra.shared_service.modules.employment_type.dtos.EmploymentTypeDTO;

import java.util.List;
import java.util.UUID;

public interface EmploymentTypeService {

    List<EmploymentTypeDTO> getAll();

    EmploymentTypeDTO getById(UUID id);

    EmploymentTypeDTO create(CreateEmploymentTypeRequest request);

    EmploymentTypeDTO update(UUID id, CreateEmploymentTypeRequest request);

    String delete(UUID id);
}
