package com.codymitra.shared_service.modules.employee_work_unit.services;

import com.codymitra.shared_service.modules.employee_work_unit.dtos.CreateEmployeeWorkUnitRequest;
import com.codymitra.shared_service.modules.employee_work_unit.dtos.EmployeeWorkUnitDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeWorkUnitService {

    List<EmployeeWorkUnitDTO> getAll();

    EmployeeWorkUnitDTO getById(UUID id);

    EmployeeWorkUnitDTO create(CreateEmployeeWorkUnitRequest request);

    EmployeeWorkUnitDTO update(UUID id, CreateEmployeeWorkUnitRequest request);

    String delete(UUID id);
}
