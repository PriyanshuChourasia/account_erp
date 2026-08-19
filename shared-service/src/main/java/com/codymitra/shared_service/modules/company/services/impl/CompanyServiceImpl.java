package com.codymitra.shared_service.modules.company.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.company.mappers.CompanyMapper;
import com.codymitra.shared_service.modules.company.repositories.CompanyRepository;
import com.codymitra.shared_service.modules.company.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getAllCompanies(){
        List<CompanyEntity> companyEntities = companyRepository.findAll();
        return companyEntities.stream().map(CompanyMapper::companyDTO).toList();
    }

    @Override
    public CompanyEntity getById(UUID id){
        return companyRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Company does not exists with this id")
        );
    }

    @Override
    public String create(CreateCompanyDTO createCompanyDTO){
        if(companyRepository.existsByName(createCompanyDTO.name())){
            throw new DataAlreadyExistsException("Company already exists");
        }
        companyRepository.save(CompanyMapper.companyEntity(createCompanyDTO));
        return "Company created successfully";
    }
}
