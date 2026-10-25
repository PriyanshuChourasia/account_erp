package com.codymitra.shared_service.modules.salary_component.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.salary_component.dtos.CreateSalaryComponentRequest;
import com.codymitra.shared_service.modules.salary_component.dtos.SalaryComponentDTO;
import com.codymitra.shared_service.modules.salary_component.entities.SalaryComponentEntity;
import com.codymitra.shared_service.modules.salary_component.mappers.SalaryComponentMapper;
import com.codymitra.shared_service.modules.salary_component.repositories.SalaryComponentRepository;
import com.codymitra.shared_service.modules.salary_component.services.SalaryComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalaryComponentServiceImpl implements SalaryComponentService {

    private final SalaryComponentRepository salaryComponentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SalaryComponentDTO> getAll() {
        return salaryComponentRepository.findAllByOrderByDisplayOrderAscNameAsc().stream()
                .map(SalaryComponentMapper::salaryComponentDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SalaryComponentDTO getById(UUID id) {
        return SalaryComponentMapper.salaryComponentDTO(findById(id));
    }

    @Override
    @Transactional
    public SalaryComponentDTO create(CreateSalaryComponentRequest request) {
        validateUnique(request.name().trim(), SalaryComponentMapper.resolveCode(request.code(), request.name()), null);
        validateCalculation(request, null);
        return SalaryComponentMapper.salaryComponentDTO(salaryComponentRepository.save(SalaryComponentMapper.salaryComponentEntity(request)));
    }

    @Override
    @Transactional
    public SalaryComponentDTO update(UUID id, CreateSalaryComponentRequest request) {
        SalaryComponentEntity entity = findById(id);
        validateUnique(request.name().trim(), SalaryComponentMapper.resolveCode(request.code(), request.name()), id);
        validateCalculation(request, id);
        return SalaryComponentMapper.salaryComponentDTO(salaryComponentRepository.save(SalaryComponentMapper.salaryComponentEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        SalaryComponentEntity entity = findById(id);
        if (salaryComponentRepository.existsByBaseComponentId(id)) {
            throw new IllegalArgumentException("Salary component is used as base by other components");
        }
        salaryComponentRepository.delete(entity);
        return "Salary component deleted successfully";
    }

    private SalaryComponentEntity findById(UUID id) {
        return salaryComponentRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such salary component found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? salaryComponentRepository.existsByName(name)
                : salaryComponentRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Salary component already exists with this name");
        }

        boolean codeTaken = id == null
                ? salaryComponentRepository.existsByCode(code)
                : salaryComponentRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("Salary component already exists with this code");
        }
    }

    private void validateCalculation(CreateSalaryComponentRequest request, UUID id) {
        switch (request.calculationType()) {
            case FLAT_AMOUNT -> {
                if (request.amount() == null) {
                    throw new IllegalArgumentException("Amount is required for flat amount calculation");
                }
            }
            case PERCENTAGE -> {
                if (request.percentage() == null) {
                    throw new IllegalArgumentException("Percentage is required for percentage calculation");
                }
                if (request.baseComponentId() == null) {
                    throw new IllegalArgumentException("Base component is required for percentage calculation");
                }
                validateBaseComponent(request.baseComponentId(), id);
            }
            default -> {
            }
        }
    }

    private void validateBaseComponent(UUID baseComponentId, UUID id) {
        if (baseComponentId.equals(id)) {
            throw new IllegalArgumentException("Salary component cannot be calculated on itself");
        }

        // walk the base chain; reaching this component would create a circular calculation
        UUID currentId = baseComponentId;
        while (currentId != null) {
            SalaryComponentEntity current = salaryComponentRepository.findById(currentId).orElseThrow(
                    () -> new DataNotFoundException("Base salary component does not exist")
            );
            if (current.getId().equals(id)) {
                throw new IllegalArgumentException("Salary component calculation would be circular");
            }
            currentId = current.getBaseComponentId();
        }
    }
}
