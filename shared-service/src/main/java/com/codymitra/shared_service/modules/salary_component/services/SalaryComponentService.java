package com.codymitra.shared_service.modules.salary_component.services;

import com.codymitra.shared_service.modules.salary_component.dtos.CreateSalaryComponentRequest;
import com.codymitra.shared_service.modules.salary_component.dtos.SalaryComponentDTO;

import java.util.List;
import java.util.UUID;

public interface SalaryComponentService {

    List<SalaryComponentDTO> getAll();

    SalaryComponentDTO getById(UUID id);

    SalaryComponentDTO create(CreateSalaryComponentRequest request);

    SalaryComponentDTO update(UUID id, CreateSalaryComponentRequest request);

    String delete(UUID id);
}
