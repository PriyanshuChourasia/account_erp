package com.codymitra.shared_service.modules.company.mappers;

import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;

public final class CompanyMapper {

    public static CompanyDTO companyDTO(CompanyEntity companyEntity){
        return new CompanyDTO(
                companyEntity.getId(),
                companyEntity.getName(),
                companyEntity.getCode()
        );
    }

    public static CompanyEntity companyEntity(CreateCompanyDTO createCompanyDTO){
        CompanyEntity company = new CompanyEntity();
        company.setName(createCompanyDTO.name());
        company.setCode(createCompanyDTO.code());
        return company;
    }
}
