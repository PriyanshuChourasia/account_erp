package com.codymitra.shared_service.modules.allocation_method.services;

import com.codymitra.shared_service.modules.allocation_method.dtos.AllocationMethodDTO;
import com.codymitra.shared_service.modules.allocation_method.dtos.CreateAllocationMethodRequest;

import java.util.List;
import java.util.UUID;

public interface AllocationMethodService {
    List<AllocationMethodDTO> getAll();
    AllocationMethodDTO getById(UUID id);
    String create(CreateAllocationMethodRequest request);
}
