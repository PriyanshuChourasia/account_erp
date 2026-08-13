package com.codymitra.shared_service.modules.country.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.modules.country.dtos.CountryDTO;
import com.codymitra.shared_service.modules.country.dtos.CreateCountryDTO;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.country.mappers.CountryMapper;
import com.codymitra.shared_service.modules.country.repositories.CountryRepository;
import com.codymitra.shared_service.modules.country.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public List<CountryDTO> getAll() {
        return countryRepository.findAll().stream().map(CountryMapper::countryDTO).toList();
    }

    @Override
    public CountryDTO create(CreateCountryDTO request) {
        if (countryRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Country already exists");
        }
        CountryEntity entity = CountryMapper.countryEntity(request);
        CountryEntity saved = countryRepository.save(entity);
        return CountryMapper.countryDTO(saved);
    }
}
