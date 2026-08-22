package com.codymitra.shared_service.modules.country.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.country.dtos.CountryDTO;
import com.codymitra.shared_service.modules.country.dtos.CreateCountryDTO;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.country.mappers.CountryMapper;
import com.codymitra.shared_service.modules.country.repositories.CountryRepository;
import com.codymitra.shared_service.modules.country.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CountryDTO> getAll() {
        return countryRepository.findAll().stream().map(CountryMapper::countryDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CountryDTO getById(UUID id) {
        return CountryMapper.countryDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CountryEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public CountryDTO create(CreateCountryDTO request) {
        if (countryRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Country already exists");
        }
        CountryEntity entity = CountryMapper.countryEntity(request);
        CountryEntity saved = countryRepository.save(entity);
        return CountryMapper.countryDTO(saved);
    }

    private CountryEntity findById(UUID id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such country found")
        );
    }
}
