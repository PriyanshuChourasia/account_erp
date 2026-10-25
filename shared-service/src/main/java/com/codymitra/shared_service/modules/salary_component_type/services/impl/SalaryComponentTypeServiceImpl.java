package com.codymitra.shared_service.modules.salary_component_type.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.salary_component_type.dtos.CreateSalaryComponentTypeRequest;
import com.codymitra.shared_service.modules.salary_component_type.dtos.SalaryComponentTypeDTO;
import com.codymitra.shared_service.modules.salary_component_type.entities.SalaryComponentTypeEntity;
import com.codymitra.shared_service.modules.salary_component_type.mappers.SalaryComponentTypeMapper;
import com.codymitra.shared_service.modules.salary_component_type.repositories.SalaryComponentTypeRepository;
import com.codymitra.shared_service.modules.salary_component_type.services.SalaryComponentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalaryComponentTypeServiceImpl implements SalaryComponentTypeService {

    private final SalaryComponentTypeRepository salaryComponentTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SalaryComponentTypeDTO> getAll() {
        return salaryComponentTypeRepository.findAll().stream()
                .map(SalaryComponentTypeMapper::salaryComponentTypeDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SalaryComponentTypeDTO getById(UUID id) {
        return SalaryComponentTypeMapper.salaryComponentTypeDTO(findById(id));
    }

    @Override
    @Transactional
    public SalaryComponentTypeDTO create(CreateSalaryComponentTypeRequest request) {
        validateUnique(request.name().trim(), SalaryComponentTypeMapper.resolveCode(request.code()), null);
        return SalaryComponentTypeMapper.salaryComponentTypeDTO(
                salaryComponentTypeRepository.save(SalaryComponentTypeMapper.salaryComponentTypeEntity(request)));
    }

    @Override
    @Transactional
    public SalaryComponentTypeDTO update(UUID id, CreateSalaryComponentTypeRequest request) {
        SalaryComponentTypeEntity entity = findById(id);
        validateUnique(request.name().trim(), SalaryComponentTypeMapper.resolveCode(request.code()), id);
        return SalaryComponentTypeMapper.salaryComponentTypeDTO(
                salaryComponentTypeRepository.save(SalaryComponentTypeMapper.salaryComponentTypeEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        salaryComponentTypeRepository.delete(findById(id));
        return "Salary component type deleted successfully";
    }

    private SalaryComponentTypeEntity findById(UUID id) {
        return salaryComponentTypeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such salary component type found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? salaryComponentTypeRepository.existsByName(name)
                : salaryComponentTypeRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Salary component type already exists with this name");
        }

        if (code == null) {
            return;
        }
        boolean codeTaken = id == null
                ? salaryComponentTypeRepository.existsByCode(code)
                : salaryComponentTypeRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("Salary component type already exists with this code");
        }
    }
}
