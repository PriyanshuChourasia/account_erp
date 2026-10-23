package com.codymitra.shared_service.modules.designation.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.designation.dtos.CreateDesignationRequest;
import com.codymitra.shared_service.modules.designation.dtos.DesignationDTO;
import com.codymitra.shared_service.modules.designation.dtos.DesignationHierarchyDTO;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import com.codymitra.shared_service.modules.designation.mappers.DesignationMapper;
import com.codymitra.shared_service.modules.designation.repositories.DesignationRepository;
import com.codymitra.shared_service.modules.designation.services.DesignationService;
import com.codymitra.shared_service.modules.designation.utils.DesignationHierarchy;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DesignationServiceImpl implements DesignationService {

    private final DesignationRepository designationRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DesignationDTO> getAll() {
        return designationRepository.findAll().stream()
                .map(DesignationMapper::designationDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DesignationHierarchyDTO> getAllWithChildren() {
        return DesignationHierarchy.designationDTO(designationRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public DesignationDTO getById(UUID id) {
        return DesignationMapper.designationDTO(findById(id));
    }

    @Override
    @Transactional
    public DesignationDTO create(CreateDesignationRequest request) {
        validateUnique(request.name(), DesignationMapper.resolveCode(request.code(), request.name()), null);
        validateParent(request.parentId(), null);
        return DesignationMapper.designationDTO(
                designationRepository.save(DesignationMapper.designationEntity(request))
        );
    }

    @Override
    @Transactional
    public DesignationDTO update(UUID id, CreateDesignationRequest request) {
        DesignationEntity entity = findById(id);
        validateUnique(request.name(), DesignationMapper.resolveCode(request.code(), request.name()), id);
        validateParent(request.parentId(), id);
        return DesignationMapper.designationDTO(
                designationRepository.save(DesignationMapper.designationEntity(entity, request))
        );
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        DesignationEntity entity = findById(id);
        if (designationRepository.existsByParentId(id)) {
            throw new DataAlreadyExistsException("Designation has child designations, remove them first");
        }
        if (!employeeRepository.findByDesignationId(id).isEmpty()) {
            throw new DataAlreadyExistsException("Designation is assigned to employees, it cannot be deleted");
        }
        designationRepository.delete(entity);
        return "Designation deleted successfully";
    }

    private DesignationEntity findById(UUID id) {
        return designationRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such designation found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        if (id == null) {
            if (designationRepository.existsByName(name.trim())) {
                throw new DataAlreadyExistsException("Designation already exists with this name");
            }
            if (designationRepository.existsByCode(code)) {
                throw new DataAlreadyExistsException("Designation already exists with this code");
            }
            return;
        }

        if (designationRepository.existsByNameAndIdNot(name.trim(), id)) {
            throw new DataAlreadyExistsException("Designation already exists with this name");
        }
        if (designationRepository.existsByCodeAndIdNot(code, id)) {
            throw new DataAlreadyExistsException("Designation already exists with this code");
        }
    }

    private void validateParent(UUID parentId, UUID id) {
        if (parentId == null) {
            return;
        }
        if (parentId.equals(id)) {
            throw new IllegalArgumentException("Designation cannot be its own parent");
        }

        // walk up from the new parent; reaching this designation would create a cycle
        UUID currentId = parentId;
        while (currentId != null) {
            DesignationEntity current = designationRepository.findById(currentId).orElseThrow(
                    () -> new DataNotFoundException("Parent designation does not exist")
            );
            if (current.getId().equals(id)) {
                throw new IllegalArgumentException("Designation cannot be moved under its own child");
            }
            currentId = current.getParentId();
        }
    }
}
