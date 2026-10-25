package com.codymitra.shared_service.modules.salary_component_type.services;

import com.codymitra.shared_service.modules.salary_component_type.dtos.CreateSalaryComponentTypeRequest;
import com.codymitra.shared_service.modules.salary_component_type.dtos.SalaryComponentTypeDTO;

import java.util.List;
import java.util.UUID;

public interface SalaryComponentTypeService {

    List<SalaryComponentTypeDTO> getAll();

    SalaryComponentTypeDTO getById(UUID id);

    SalaryComponentTypeDTO create(CreateSalaryComponentTypeRequest request);

    SalaryComponentTypeDTO update(UUID id, CreateSalaryComponentTypeRequest request);

    String delete(UUID id);
}
