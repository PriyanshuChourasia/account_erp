package com.codymitra.shared_service.modules.storage_location.services.impl;

import java.util.UUID;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.storage_location.dtos.CreateStorageLocationRequestDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationHierarchyDTO;
import com.codymitra.shared_service.modules.storage_location.entities.StorageLocationEntity;
import com.codymitra.shared_service.modules.storage_location.mappers.StorageLocationMapper;
import com.codymitra.shared_service.modules.storage_location.repositories.StorageLocationRepository;
import com.codymitra.shared_service.modules.storage_location.services.StorageLocationService;
import com.codymitra.shared_service.modules.storage_location.utils.StorageLocationHierarchy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageLocationServiceImpl implements StorageLocationService {

    private final StorageLocationRepository storageLocationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StorageLocationDTO> getAll() {
        return storageLocationRepository.findAll().stream()
                .map(StorageLocationMapper::storageLocationDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StorageLocationHierarchyDTO> getAllWithChildren() {
        return StorageLocationHierarchy.storageLocationDTO(storageLocationRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public StorageLocationDTO getById(UUID id) {
        return StorageLocationMapper.storageLocationDTO(findById(id));
    }

    @Override
    @Transactional
    public StorageLocationDTO create(CreateStorageLocationRequestDTO request) {
        if (storageLocationRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Storage location already exists with this name");
        }
        StorageLocationEntity storageLocation = StorageLocationMapper.storageLocationEntity(request);
        return StorageLocationMapper.storageLocationDTO(storageLocationRepository.save(storageLocation));
    }

    @Override
    @Transactional
    public StorageLocationDTO update(UUID id, CreateStorageLocationRequestDTO request) {
        StorageLocationEntity storageLocation = findById(id);
        if (storageLocationRepository.existsByNameAndIdNot(request.name(), id)) {
            throw new DataAlreadyExistsException("Storage location already exists with this name");
        }
        StorageLocationEntity updated = StorageLocationMapper.storageLocationEntity(storageLocation, request);
        return StorageLocationMapper.storageLocationDTO(storageLocationRepository.save(updated));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        storageLocationRepository.delete(findById(id));
        return "Storage location deleted successfully";
    }

    private StorageLocationEntity findById(UUID id) {
        return storageLocationRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Storage location does not exist")
        );
    }
}
