package com.codymitra.shared_service.modules.employee_employment_detail.services;

import com.codymitra.shared_service.modules.employee_employment_detail.dtos.CreateEmployeeEmploymentDetailRequest;
import com.codymitra.shared_service.modules.employee_employment_detail.dtos.EmployeeEmploymentDetailDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeEmploymentDetailService {

    List<EmployeeEmploymentDetailDTO> getAll();

    EmployeeEmploymentDetailDTO getById(UUID id);

    EmployeeEmploymentDetailDTO create(CreateEmployeeEmploymentDetailRequest request);

    EmployeeEmploymentDetailDTO update(UUID id, CreateEmployeeEmploymentDetailRequest request);

    String delete(UUID id);
}
