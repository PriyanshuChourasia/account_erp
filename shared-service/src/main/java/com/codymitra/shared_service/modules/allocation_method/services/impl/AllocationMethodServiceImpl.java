package com.codymitra.shared_service.modules.allocation_method.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.allocation_method.dtos.AllocationMethodDTO;
import com.codymitra.shared_service.modules.allocation_method.dtos.CreateAllocationMethodRequest;
import com.codymitra.shared_service.modules.allocation_method.entities.AllocationMethodEntity;
import com.codymitra.shared_service.modules.allocation_method.mappers.AllocationMethodMapper;
import com.codymitra.shared_service.modules.allocation_method.repositories.AllocationMethodRepository;
import com.codymitra.shared_service.modules.allocation_method.services.AllocationMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AllocationMethodServiceImpl implements AllocationMethodService {

    private final AllocationMethodRepository allocationMethodRepository;

    @Override
    public List<AllocationMethodDTO> getAll() {
        return allocationMethodRepository.findAll().stream()
                .map(AllocationMethodMapper::allocationMethodDTO)
                .toList();
    }

    @Override
    public AllocationMethodDTO getById(UUID id) {
        AllocationMethodEntity entity = allocationMethodRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Allocation method not found"));
        return AllocationMethodMapper.allocationMethodDTO(entity);
    }

    @Override
    @Transactional
    public String create(CreateAllocationMethodRequest request) {
        if (allocationMethodRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Allocation method already exists with this name");
        }
        AllocationMethodEntity entity = AllocationMethodMapper.allocationMethodEntity(request);
        allocationMethodRepository.save(entity);
        return "Allocation method created successfully";
    }
}
