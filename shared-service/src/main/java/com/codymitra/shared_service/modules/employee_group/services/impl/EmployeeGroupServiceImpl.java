package com.codymitra.shared_service.modules.employee_group.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employee_group.dtos.CreateEmployeeGroupRequest;
import com.codymitra.shared_service.modules.employee_group.dtos.EmployeeGroupDTO;
import com.codymitra.shared_service.modules.employee_group.entities.EmployeeGroupEntity;
import com.codymitra.shared_service.modules.employee_group.mappers.EmployeeGroupMapper;
import com.codymitra.shared_service.modules.employee_group.repositories.EmployeeGroupRepository;
import com.codymitra.shared_service.modules.employee_group.services.EmployeeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeGroupServiceImpl implements EmployeeGroupService {

    private final EmployeeGroupRepository employeeGroupRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeGroupDTO> getAll() {
        return employeeGroupRepository.findAll().stream()
                .map(EmployeeGroupMapper::employeeGroupDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeGroupDTO getById(UUID id) {
        return EmployeeGroupMapper.employeeGroupDTO(findById(id));
    }

    @Override
    @Transactional
    public EmployeeGroupDTO create(CreateEmployeeGroupRequest request) {
        validateUnique(request.name(), EmployeeGroupMapper.resolveCode(request.name()), null);
        return EmployeeGroupMapper.employeeGroupDTO(employeeGroupRepository.save(EmployeeGroupMapper.employeeGroupEntity(request)));
    }

    @Override
    @Transactional
    public EmployeeGroupDTO update(UUID id, CreateEmployeeGroupRequest request) {
        EmployeeGroupEntity entity = findById(id);
        validateUnique(request.name(), EmployeeGroupMapper.resolveCode(request.name()), id);
        return EmployeeGroupMapper.employeeGroupDTO(employeeGroupRepository.save(EmployeeGroupMapper.employeeGroupEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        employeeGroupRepository.delete(findById(id));
        return "Employee group deleted successfully";
    }

    private EmployeeGroupEntity findById(UUID id) {
        return employeeGroupRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such employee group found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        if (id == null) {
            if (employeeGroupRepository.existsByName(name)) {
                throw new DataAlreadyExistsException("Employee group already exists with this name");
            }
            return;
        }

        if (employeeGroupRepository.existsByNameAndIdNot(name, id)) {
            throw new DataAlreadyExistsException("Employee group already exists with this name");
        }
    }
}
