package com.codymitra.shared_service.modules.allocation_method.mappers;

import com.codymitra.shared_service.modules.allocation_method.dtos.AllocationMethodDTO;
import com.codymitra.shared_service.modules.allocation_method.dtos.CreateAllocationMethodRequest;
import com.codymitra.shared_service.modules.allocation_method.entities.AllocationMethodEntity;

public final class AllocationMethodMapper {

    public static AllocationMethodDTO allocationMethodDTO(AllocationMethodEntity entity) {
        return new AllocationMethodDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static AllocationMethodEntity allocationMethodEntity(CreateAllocationMethodRequest request) {
        AllocationMethodEntity entity = new AllocationMethodEntity();
        entity.setName(request.name());
        entity.setCode(request.code());
        entity.setDescription(request.description());
        entity.setActive(true);
        return entity;
    }
}
