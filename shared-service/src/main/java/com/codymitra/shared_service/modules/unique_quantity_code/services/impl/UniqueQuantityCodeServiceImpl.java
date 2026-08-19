package com.codymitra.shared_service.modules.unique_quantity_code.services.impl;

import java.util.UUID;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.CreateUniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.UniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.entities.UniqueQuantityCodeEntity;
import com.codymitra.shared_service.modules.unique_quantity_code.mappers.UniqueQuantityCodeMapper;
import com.codymitra.shared_service.modules.unique_quantity_code.repositories.UniqueQuantityCodeRepository;
import com.codymitra.shared_service.modules.unique_quantity_code.services.UniqueQuantityCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniqueQuantityCodeServiceImpl implements UniqueQuantityCodeService {

    private final UniqueQuantityCodeRepository uqcRepository;

    @Override
    public List<UniqueQuantityCodeDTO> getAll() {
        return uqcRepository.findAll().stream().map(UniqueQuantityCodeMapper::uqcDTO).toList();
    }

    @Override
    public UniqueQuantityCodeDTO getById(UUID id) {
        return UniqueQuantityCodeMapper.uqcDTO(findById(id));
    }

    @Override
    public UniqueQuantityCodeDTO create(CreateUniqueQuantityCodeDTO request) {
        if (uqcRepository.existsByCode(request.code().toUpperCase())) {
            throw new DataAlreadyExistsException("UQC with code " + request.code() + " already exists");
        }
        if (uqcRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("UQC already exists");
        }
        UniqueQuantityCodeEntity saved = uqcRepository.save(UniqueQuantityCodeMapper.uqcEntity(request));
        return UniqueQuantityCodeMapper.uqcDTO(saved);
    }

    @Override
    public UniqueQuantityCodeDTO update(UUID id, CreateUniqueQuantityCodeDTO request) {
        UniqueQuantityCodeEntity uqc = findById(id);
        if (uqcRepository.existsByCode(request.code().toUpperCase()) && !uqc.getCode().equalsIgnoreCase(request.code())) {
            throw new DataAlreadyExistsException("UQC with code " + request.code() + " already exists");
        }
        if (uqcRepository.existsByName(request.name()) && !uqc.getName().equals(request.name())) {
            throw new DataAlreadyExistsException("UQC already exists");
        }
        uqc.setName(request.name());
        uqc.setCode(request.code().toUpperCase());
        uqc.setAlias(request.alias());
        uqc.setDescription(request.description());
        return UniqueQuantityCodeMapper.uqcDTO(uqcRepository.save(uqc));
    }

    @Override
    public String delete(UUID id) {
        uqcRepository.delete(findById(id));
        return "UQC deleted successfully";
    }

    private UniqueQuantityCodeEntity findById(UUID id) {
        return uqcRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("UQC does not exist")
        );
    }
}
