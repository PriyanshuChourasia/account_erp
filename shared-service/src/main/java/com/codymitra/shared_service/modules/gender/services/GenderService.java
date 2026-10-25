package com.codymitra.shared_service.modules.gender.services;

import com.codymitra.shared_service.modules.gender.dtos.CreateGenderRequest;
import com.codymitra.shared_service.modules.gender.dtos.GenderDTO;

import java.util.List;
import java.util.UUID;

public interface GenderService {

    List<GenderDTO> getAll();

    GenderDTO getById(UUID id);

    GenderDTO create(CreateGenderRequest request);

    GenderDTO update(UUID id, CreateGenderRequest request);

    String delete(UUID id);
}
