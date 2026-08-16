package com.codymitra.shared_service.modules.uqc.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.uqc.dtos.CreateUQCDTO;
import com.codymitra.shared_service.modules.uqc.dtos.UQCDTO;
import com.codymitra.shared_service.modules.uqc.entities.UQCEntity;
import com.codymitra.shared_service.modules.uqc.mappers.UQCMapper;
import com.codymitra.shared_service.modules.uqc.repositories.UQCRepository;
import com.codymitra.shared_service.modules.uqc.services.UQCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UQCServiceImpl implements UQCService {

    private final UQCRepository uqcRepository;

    @Override
    public List<UQCDTO> getAll() {
        return uqcRepository.findAll().stream().map(UQCMapper::uqcDTO).toList();
    }

    @Override
    public UQCDTO getById(Long id) {
        return UQCMapper.uqcDTO(findById(id));
    }

    @Override
    public UQCDTO create(CreateUQCDTO request) {
        if (uqcRepository.existsByCode(request.code().toUpperCase())) {
            throw new DataAlreadyExistsException("UQC with code " + request.code() + " already exists");
        }
        if (uqcRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("UQC already exists");
        }
        UQCEntity saved = uqcRepository.save(UQCMapper.uqcEntity(request));
        return UQCMapper.uqcDTO(saved);
    }

    @Override
    public UQCDTO update(Long id, CreateUQCDTO request) {
        UQCEntity uqc = findById(id);
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
        return UQCMapper.uqcDTO(uqcRepository.save(uqc));
    }

    @Override
    public String delete(Long id) {
        uqcRepository.delete(findById(id));
        return "UQC deleted successfully";
    }

    private UQCEntity findById(Long id) {
        return uqcRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("UQC does not exist")
        );
    }
}
