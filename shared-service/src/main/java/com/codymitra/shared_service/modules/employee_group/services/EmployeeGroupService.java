package com.codymitra.shared_service.modules.employee_group.services;

import com.codymitra.shared_service.modules.employee_group.dtos.CreateEmployeeGroupRequest;
import com.codymitra.shared_service.modules.employee_group.dtos.EmployeeGroupDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeGroupService {

    List<EmployeeGroupDTO> getAll();

    EmployeeGroupDTO getById(UUID id);

    EmployeeGroupDTO create(CreateEmployeeGroupRequest request);

    EmployeeGroupDTO update(UUID id, CreateEmployeeGroupRequest request);

    String delete(UUID id);
}
