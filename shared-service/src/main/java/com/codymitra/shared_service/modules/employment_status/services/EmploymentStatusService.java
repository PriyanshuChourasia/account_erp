package com.codymitra.shared_service.modules.employment_status.services;

import com.codymitra.shared_service.modules.employment_status.dtos.CreateEmploymentStatusRequest;
import com.codymitra.shared_service.modules.employment_status.dtos.EmploymentStatusDTO;

import java.util.List;
import java.util.UUID;

public interface EmploymentStatusService {

    List<EmploymentStatusDTO> getAll();

    EmploymentStatusDTO getById(UUID id);

    EmploymentStatusDTO create(CreateEmploymentStatusRequest request);

    EmploymentStatusDTO update(UUID id, CreateEmploymentStatusRequest request);

    String delete(UUID id);
}
