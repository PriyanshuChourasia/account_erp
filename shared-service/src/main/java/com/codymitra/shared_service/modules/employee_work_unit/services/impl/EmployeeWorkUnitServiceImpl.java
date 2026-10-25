package com.codymitra.shared_service.modules.employee_work_unit.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employee_work_unit.dtos.CreateEmployeeWorkUnitRequest;
import com.codymitra.shared_service.modules.employee_work_unit.dtos.EmployeeWorkUnitDTO;
import com.codymitra.shared_service.modules.employee_work_unit.entities.EmployeeWorkUnitEntity;
import com.codymitra.shared_service.modules.employee_work_unit.mappers.EmployeeWorkUnitMapper;
import com.codymitra.shared_service.modules.employee_work_unit.repositories.EmployeeWorkUnitRepository;
import com.codymitra.shared_service.modules.employee_work_unit.services.EmployeeWorkUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeWorkUnitServiceImpl implements EmployeeWorkUnitService {

    private final EmployeeWorkUnitRepository employeeWorkUnitRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeWorkUnitDTO> getAll() {
        return employeeWorkUnitRepository.findAll().stream()
                .map(EmployeeWorkUnitMapper::employeeWorkUnitDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeWorkUnitDTO getById(UUID id) {
        return EmployeeWorkUnitMapper.employeeWorkUnitDTO(findById(id));
    }

    @Override
    @Transactional
    public EmployeeWorkUnitDTO create(CreateEmployeeWorkUnitRequest request) {
        validateUnique(request.name().trim(), EmployeeWorkUnitMapper.resolveSymbol(request.symbol()), null);
        return EmployeeWorkUnitMapper.employeeWorkUnitDTO(employeeWorkUnitRepository.save(
                EmployeeWorkUnitMapper.employeeWorkUnitEntity(request)));
    }

    @Override
    @Transactional
    public EmployeeWorkUnitDTO update(UUID id, CreateEmployeeWorkUnitRequest request) {
        EmployeeWorkUnitEntity entity = findById(id);
        validateUnique(request.name().trim(), EmployeeWorkUnitMapper.resolveSymbol(request.symbol()), id);
        return EmployeeWorkUnitMapper.employeeWorkUnitDTO(employeeWorkUnitRepository.save(
                EmployeeWorkUnitMapper.employeeWorkUnitEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        employeeWorkUnitRepository.delete(findById(id));
        return "Employee work unit deleted successfully";
    }

    private EmployeeWorkUnitEntity findById(UUID id) {
        return employeeWorkUnitRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such employee work unit found with this id")
        );
    }

    private void validateUnique(String name, String symbol, UUID id) {
        boolean nameTaken = id == null
                ? employeeWorkUnitRepository.existsByName(name)
                : employeeWorkUnitRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Employee work unit already exists with this name");
        }

        if (symbol == null) {
            return;
        }
        boolean symbolTaken = id == null
                ? employeeWorkUnitRepository.existsBySymbol(symbol)
                : employeeWorkUnitRepository.existsBySymbolAndIdNot(symbol, id);
        if (symbolTaken) {
            throw new DataAlreadyExistsException("Employee work unit already exists with this symbol");
        }
    }
}
