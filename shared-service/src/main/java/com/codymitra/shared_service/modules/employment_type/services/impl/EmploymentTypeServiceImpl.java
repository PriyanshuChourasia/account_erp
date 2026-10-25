package com.codymitra.shared_service.modules.employment_type.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employment_type.dtos.CreateEmploymentTypeRequest;
import com.codymitra.shared_service.modules.employment_type.dtos.EmploymentTypeDTO;
import com.codymitra.shared_service.modules.employment_type.entities.EmploymentTypeEntity;
import com.codymitra.shared_service.modules.employment_type.mappers.EmploymentTypeMapper;
import com.codymitra.shared_service.modules.employment_type.repositories.EmploymentTypeRepository;
import com.codymitra.shared_service.modules.employment_type.services.EmploymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmploymentTypeServiceImpl implements EmploymentTypeService {

    private final EmploymentTypeRepository employmentTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmploymentTypeDTO> getAll() {
        return employmentTypeRepository.findAll().stream()
                .map(EmploymentTypeMapper::employmentTypeDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmploymentTypeDTO getById(UUID id) {
        return EmploymentTypeMapper.employmentTypeDTO(findById(id));
    }

    @Override
    @Transactional
    public EmploymentTypeDTO create(CreateEmploymentTypeRequest request) {
        validateUnique(request.name().trim(), EmploymentTypeMapper.resolveCode(request.code()), null);
        return EmploymentTypeMapper.employmentTypeDTO(
                employmentTypeRepository.save(EmploymentTypeMapper.employmentTypeEntity(request)));
    }

    @Override
    @Transactional
    public EmploymentTypeDTO update(UUID id, CreateEmploymentTypeRequest request) {
        EmploymentTypeEntity entity = findById(id);
        validateUnique(request.name().trim(), EmploymentTypeMapper.resolveCode(request.code()), id);
        return EmploymentTypeMapper.employmentTypeDTO(
                employmentTypeRepository.save(EmploymentTypeMapper.employmentTypeEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        employmentTypeRepository.delete(findById(id));
        return "Employment type deleted successfully";
    }

    private EmploymentTypeEntity findById(UUID id) {
        return employmentTypeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such employment type found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? employmentTypeRepository.existsByName(name)
                : employmentTypeRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Employment type already exists with this name");
        }

        if (code == null) {
            return;
        }
        boolean codeTaken = id == null
                ? employmentTypeRepository.existsByCode(code)
                : employmentTypeRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("Employment type already exists with this code");
        }
    }
}
