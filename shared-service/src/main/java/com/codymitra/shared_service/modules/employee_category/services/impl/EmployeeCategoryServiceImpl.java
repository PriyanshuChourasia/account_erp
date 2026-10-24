package com.codymitra.shared_service.modules.employee_category.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employee_category.dtos.CreateEmployeeCategoryRequest;
import com.codymitra.shared_service.modules.employee_category.dtos.EmployeeCategoryDTO;
import com.codymitra.shared_service.modules.employee_category.entities.EmployeeCategoryEntity;
import com.codymitra.shared_service.modules.employee_category.mappers.EmployeeCategoryMapper;
import com.codymitra.shared_service.modules.employee_category.repositories.EmployeeCategoryRepository;
import com.codymitra.shared_service.modules.employee_category.services.EmployeeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeCategoryServiceImpl implements EmployeeCategoryService {

    private final EmployeeCategoryRepository employeeCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeCategoryDTO> getAll() {
        return employeeCategoryRepository.findAll().stream()
                .map(EmployeeCategoryMapper::employeeCategoryDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeCategoryDTO getById(UUID id) {
        return EmployeeCategoryMapper.employeeCategoryDTO(findById(id));
    }

    @Override
    @Transactional
    public EmployeeCategoryDTO create(CreateEmployeeCategoryRequest request) {
        validateUnique(request.name().trim(), EmployeeCategoryMapper.resolveAlias(request.alias()), null);
        return EmployeeCategoryMapper.employeeCategoryDTO(employeeCategoryRepository.save(EmployeeCategoryMapper.employeeCategoryEntity(request)));
    }

    @Override
    @Transactional
    public EmployeeCategoryDTO update(UUID id, CreateEmployeeCategoryRequest request) {
        EmployeeCategoryEntity entity = findById(id);
        validateUnique(request.name().trim(), EmployeeCategoryMapper.resolveAlias(request.alias()), id);
        return EmployeeCategoryMapper.employeeCategoryDTO(employeeCategoryRepository.save(EmployeeCategoryMapper.employeeCategoryEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        employeeCategoryRepository.delete(findById(id));
        return "Employee category deleted successfully";
    }

    private EmployeeCategoryEntity findById(UUID id) {
        return employeeCategoryRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such employee category found with this id")
        );
    }

    private void validateUnique(String name, String alias, UUID id) {
        boolean nameTaken = id == null
                ? employeeCategoryRepository.existsByName(name)
                : employeeCategoryRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Employee category already exists with this name");
        }

        if (alias == null) {
            return;
        }
        boolean aliasTaken = id == null
                ? employeeCategoryRepository.existsByAlias(alias)
                : employeeCategoryRepository.existsByAliasAndIdNot(alias, id);
        if (aliasTaken) {
            throw new DataAlreadyExistsException("Employee category already exists with this alias");
        }
    }
}
