package com.codymitra.shared_service.modules.storage_unit.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.storage_unit.dtos.StorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.dtos.CreateStorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.entities.StorageUnitEntity;
import com.codymitra.shared_service.modules.storage_unit.mappers.StorageUnitMapper;
import com.codymitra.shared_service.modules.storage_unit.repositories.StorageUnitRepository;
import com.codymitra.shared_service.modules.storage_unit.services.StorageUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageUnitServiceImpl implements StorageUnitService {

    private final StorageUnitRepository storageUnitRepository;



    @Override
    public StorageUnitEntity show(UUID id){
        return storageUnitRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Storage Unit does not exists")
        );
    }

    @Override
    public List<StorageUnitDTO> getAllStorageUnits(){
        List<StorageUnitEntity> storageUnitEntities = storageUnitRepository.findAll();
        return storageUnitEntities.stream().map(StorageUnitMapper::storageUnitDTO).toList();
    }

    @Override
    public String create(CreateStorageUnitDTO createStorageUnitDTO){
        if(storageUnitRepository.existsByName(createStorageUnitDTO.name())){
            throw new DataAlreadyExistsException("Storage Unit already exists");
        }
        storageUnitRepository.save(StorageUnitMapper.storageUnitEntity(createStorageUnitDTO));
        return "Storage Unit created successfully";
    }
}
