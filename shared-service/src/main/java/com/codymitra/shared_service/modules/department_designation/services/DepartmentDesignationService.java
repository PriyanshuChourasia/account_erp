package com.codymitra.shared_service.modules.department_designation.services;

import com.codymitra.shared_service.modules.department_designation.dtos.CreateDepartmentDesignationRequest;
import com.codymitra.shared_service.modules.department_designation.dtos.DepartmentDesignationDTO;

import java.util.List;
import java.util.UUID;

public interface DepartmentDesignationService {

    List<DepartmentDesignationDTO> getAll();

    DepartmentDesignationDTO getById(UUID id);

    List<DepartmentDesignationDTO> getByDepartmentId(UUID departmentId);

    List<DepartmentDesignationDTO> getByDesignationId(UUID designationId);

    DepartmentDesignationDTO create(CreateDepartmentDesignationRequest request);

    DepartmentDesignationDTO update(UUID id, CreateDepartmentDesignationRequest request);

    String delete(UUID id);
}
