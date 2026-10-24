package com.codymitra.shared_service.modules.blood_group.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.blood_group.dtos.BloodGroupDTO;
import com.codymitra.shared_service.modules.blood_group.dtos.CreateBloodGroupRequest;
import com.codymitra.shared_service.modules.blood_group.entities.BloodGroupEntity;
import com.codymitra.shared_service.modules.blood_group.mappers.BloodGroupMapper;
import com.codymitra.shared_service.modules.blood_group.repositories.BloodGroupRepository;
import com.codymitra.shared_service.modules.blood_group.services.BloodGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BloodGroupServiceImpl implements BloodGroupService {

    private final BloodGroupRepository bloodGroupRepository;

    @Override
    public List<BloodGroupDTO> getAll() {
        return bloodGroupRepository.findAll().stream()
                .map(BloodGroupMapper::bloodGroupDTO)
                .toList();
    }

    @Override
    public BloodGroupDTO getById(UUID id) {
        BloodGroupEntity entity = bloodGroupRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Blood group not found"));
        return BloodGroupMapper.bloodGroupDTO(entity);
    }

    @Override
    @Transactional
    public String create(CreateBloodGroupRequest request) {
        if (bloodGroupRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Blood group already exists with this name");
        }
        BloodGroupEntity entity = BloodGroupMapper.bloodGroupEntity(request);
        bloodGroupRepository.save(entity);
        return "Blood group created successfully";
    }
}
