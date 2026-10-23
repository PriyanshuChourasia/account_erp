package com.codymitra.shared_service.modules.employee_category.services;

import com.codymitra.shared_service.modules.employee_category.dtos.CreateEmployeeCategoryRequest;
import com.codymitra.shared_service.modules.employee_category.dtos.EmployeeCategoryDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeCategoryService {

    List<EmployeeCategoryDTO> getAll();

    EmployeeCategoryDTO getById(UUID id);

    EmployeeCategoryDTO create(CreateEmployeeCategoryRequest request);

    EmployeeCategoryDTO update(UUID id, CreateEmployeeCategoryRequest request);

    String delete(UUID id);
}
