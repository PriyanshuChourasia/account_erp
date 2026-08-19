package com.codymitra.shared_service.modules.company.services;

import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    List<CompanyDTO> getAllCompanies();

    String create(CreateCompanyDTO createCompanyDTO);

    CompanyEntity getById(UUID id);
}
