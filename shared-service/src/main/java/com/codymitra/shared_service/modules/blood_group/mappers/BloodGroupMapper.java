package com.codymitra.shared_service.modules.blood_group.mappers;

import com.codymitra.shared_service.modules.blood_group.dtos.BloodGroupDTO;
import com.codymitra.shared_service.modules.blood_group.dtos.CreateBloodGroupRequest;
import com.codymitra.shared_service.modules.blood_group.entities.BloodGroupEntity;

public final class BloodGroupMapper {

    public static BloodGroupDTO bloodGroupDTO(BloodGroupEntity entity) {
        return new BloodGroupDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static BloodGroupEntity bloodGroupEntity(CreateBloodGroupRequest request) {
        BloodGroupEntity entity = new BloodGroupEntity();
        entity.setName(request.name());
        entity.setCode(request.code());
        entity.setDescription(request.description());
        entity.setActive(true);
        return entity;
    }
}
