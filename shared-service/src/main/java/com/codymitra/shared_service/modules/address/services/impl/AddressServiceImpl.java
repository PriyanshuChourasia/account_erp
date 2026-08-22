package com.codymitra.shared_service.modules.address.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.address.dtos.AddressDTO;
import com.codymitra.shared_service.modules.address.dtos.CreateAddressDTO;
import com.codymitra.shared_service.modules.address.entities.AddressEntity;
import com.codymitra.shared_service.modules.address.mappers.AddressMapper;
import com.codymitra.shared_service.modules.address.repositories.AddressRepository;
import com.codymitra.shared_service.modules.address.services.AddressService;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.country.repositories.CountryRepository;
import com.codymitra.shared_service.modules.state.entities.StateEntity;
import com.codymitra.shared_service.modules.state.repositories.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    @Override
    public AddressDTO create(CreateAddressDTO createAddressDTO, String addressableId, String addressableType) {
        StateEntity state = stateRepository.findById(createAddressDTO.stateId()).orElseThrow(
                () -> new DataNotFoundException("No such state exists")
        );
        CountryEntity country = countryRepository.findById(createAddressDTO.countryId()).orElseThrow(
                () -> new DataNotFoundException("No such country exists")
        );

        AddressEntity entity = AddressMapper.addressEntity(createAddressDTO, state, country);
        entity.setAddressableId(addressableId);
        entity.setAddressableType(addressableType);

        AddressEntity saved = addressRepository.save(entity);
        return AddressMapper.addressDTO(saved);
    }
}
