package com.codymitra.shared_service.modules.storage_location.mappers;

import com.codymitra.shared_service.modules.storage_location.dtos.CreateStorageLocationRequestDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationDTO;
import com.codymitra.shared_service.modules.storage_location.entities.StorageLocationEntity;

public final class StorageLocationMapper {

    public static StorageLocationDTO storageLocationDTO(StorageLocationEntity storageLocation) {
        return new StorageLocationDTO(
                storageLocation.getId(),
                storageLocation.getName(),
                storageLocation.getCode(),
                storageLocation.getAlias(),
                storageLocation.getParentId(),
                storageLocation.getDescription(),
                storageLocation.getActive()
        );
    }

    public static StorageLocationEntity storageLocationEntity(CreateStorageLocationRequestDTO request) {
        StorageLocationEntity storageLocation = new StorageLocationEntity();
        storageLocation.setActive(true);
        return applyRequest(storageLocation, request);
    }

    public static StorageLocationEntity storageLocationEntity(StorageLocationEntity storageLocation, CreateStorageLocationRequestDTO request) {
        return applyRequest(storageLocation, request);
    }

    private static StorageLocationEntity applyRequest(StorageLocationEntity storageLocation, CreateStorageLocationRequestDTO request) {
        storageLocation.setName(request.name());
        storageLocation.setCode(request.code() != null ? request.code().toUpperCase() : null);
        storageLocation.setAlias(request.alias());
        storageLocation.setDescription(request.description());
        storageLocation.setParentId(request.parentId());
        return storageLocation;
    }
}
