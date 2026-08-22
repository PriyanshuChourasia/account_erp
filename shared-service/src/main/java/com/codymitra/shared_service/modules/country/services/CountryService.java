package com.codymitra.shared_service.modules.country.services;

import com.codymitra.shared_service.modules.country.dtos.CountryDTO;
import com.codymitra.shared_service.modules.country.dtos.CreateCountryDTO;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    List<CountryDTO> getAll();

    CountryDTO getById(UUID id);

    CountryEntity getEntityById(UUID id);

    CountryDTO create(CreateCountryDTO request);
}
