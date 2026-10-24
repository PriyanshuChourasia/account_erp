package com.codymitra.shared_service.modules.blood_group.services;

import com.codymitra.shared_service.modules.blood_group.dtos.BloodGroupDTO;
import com.codymitra.shared_service.modules.blood_group.dtos.CreateBloodGroupRequest;

import java.util.List;
import java.util.UUID;

public interface BloodGroupService {
    List<BloodGroupDTO> getAll();
    BloodGroupDTO getById(UUID id);
    String create(CreateBloodGroupRequest request);
}
