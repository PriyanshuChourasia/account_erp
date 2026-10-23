package com.codymitra.shared_service.modules.employee.services;

import com.codymitra.shared_service.modules.employee.dtos.CreateEmployeeRequest;
import com.codymitra.shared_service.modules.employee.dtos.EmployeeDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeDTO> getAll();

    EmployeeDTO getById(UUID id);

    List<EmployeeDTO> getByDepartment(UUID departmentId);

    EmployeeDTO create(CreateEmployeeRequest request);

    EmployeeDTO update(UUID id, CreateEmployeeRequest request);

    String delete(UUID id);
}
