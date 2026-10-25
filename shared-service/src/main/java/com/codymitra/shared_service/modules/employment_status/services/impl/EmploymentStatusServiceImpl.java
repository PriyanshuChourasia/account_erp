package com.codymitra.shared_service.modules.employment_status.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employment_status.dtos.CreateEmploymentStatusRequest;
import com.codymitra.shared_service.modules.employment_status.dtos.EmploymentStatusDTO;
import com.codymitra.shared_service.modules.employment_status.entities.EmploymentStatusEntity;
import com.codymitra.shared_service.modules.employment_status.mappers.EmploymentStatusMapper;
import com.codymitra.shared_service.modules.employment_status.repositories.EmploymentStatusRepository;
import com.codymitra.shared_service.modules.employment_status.services.EmploymentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmploymentStatusServiceImpl implements EmploymentStatusService {

    private final EmploymentStatusRepository employmentStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmploymentStatusDTO> getAll() {
        return employmentStatusRepository.findAll().stream()
                .map(EmploymentStatusMapper::employmentStatusDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmploymentStatusDTO getById(UUID id) {
        return EmploymentStatusMapper.employmentStatusDTO(findById(id));
    }

    @Override
    @Transactional
    public EmploymentStatusDTO create(CreateEmploymentStatusRequest request) {
        validateUnique(request.name().trim(), EmploymentStatusMapper.resolveCode(request.code()), null);
        return EmploymentStatusMapper.employmentStatusDTO(
                employmentStatusRepository.save(EmploymentStatusMapper.employmentStatusEntity(request)));
    }

    @Override
    @Transactional
    public EmploymentStatusDTO update(UUID id, CreateEmploymentStatusRequest request) {
        EmploymentStatusEntity entity = findById(id);
        validateUnique(request.name().trim(), EmploymentStatusMapper.resolveCode(request.code()), id);
        return EmploymentStatusMapper.employmentStatusDTO(
                employmentStatusRepository.save(EmploymentStatusMapper.employmentStatusEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        employmentStatusRepository.delete(findById(id));
        return "Employment status deleted successfully";
    }

    private EmploymentStatusEntity findById(UUID id) {
        return employmentStatusRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such employment status found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? employmentStatusRepository.existsByName(name)
                : employmentStatusRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Employment status already exists with this name");
        }

        if (code == null) {
            return;
        }
        boolean codeTaken = id == null
                ? employmentStatusRepository.existsByCode(code)
                : employmentStatusRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("Employment status already exists with this code");
        }
    }
}
