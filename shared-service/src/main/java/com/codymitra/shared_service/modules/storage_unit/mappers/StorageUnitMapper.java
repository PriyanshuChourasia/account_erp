package com.codymitra.shared_service.modules.storage_unit.mappers;

import com.codymitra.shared_service.modules.storage_unit.dtos.StorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.dtos.CreateStorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.entities.StorageUnitEntity;

public final class StorageUnitMapper {

    public static StorageUnitDTO storageUnitDTO(StorageUnitEntity storageUnitEntity){
        return new StorageUnitDTO(
                storageUnitEntity.getId(),
                storageUnitEntity.getName(),
                storageUnitEntity.getCode(),
                storageUnitEntity.getDescription()
        );
    }

    public static StorageUnitEntity storageUnitEntity(CreateStorageUnitDTO storageUnitDTO){
        StorageUnitEntity storageUnit = new StorageUnitEntity();
        storageUnit.setName(storageUnitDTO.name());
        storageUnit.setCode(storageUnitDTO.code());
        storageUnit.setDescription(storageUnitDTO.description());
        return storageUnit;
    }
}
