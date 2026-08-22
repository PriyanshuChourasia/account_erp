package com.codymitra.shared_service.modules.company.services;

import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    List<CompanyDTO> getAll();

    CompanyDTO getById(UUID id);

    CompanyEntity getEntityById(UUID id);

    CompanyDTO create(CreateCompanyDTO request);

    CompanyDTO update(UUID id, CreateCompanyDTO request);

    String delete(UUID id);
}
