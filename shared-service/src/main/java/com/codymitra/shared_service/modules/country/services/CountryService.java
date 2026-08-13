package com.codymitra.shared_service.modules.country.services;

import com.codymitra.shared_service.modules.country.dtos.CountryDTO;
import com.codymitra.shared_service.modules.country.dtos.CreateCountryDTO;

import java.util.List;

public interface CountryService {

    List<CountryDTO> getAll();
    CountryDTO create(CreateCountryDTO request);
}
