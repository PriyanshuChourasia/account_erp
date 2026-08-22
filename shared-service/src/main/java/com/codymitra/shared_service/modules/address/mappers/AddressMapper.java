package com.codymitra.shared_service.modules.address.mappers;

import com.codymitra.shared_service.modules.address.dtos.AddressDTO;
import com.codymitra.shared_service.modules.address.dtos.CreateAddressDTO;
import com.codymitra.shared_service.modules.address.entities.AddressEntity;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.state.entities.StateEntity;

public final class AddressMapper {

    public static AddressDTO addressDTO(AddressEntity entity) {
        return new AddressDTO(
                entity.getId(),
                entity.getAddressType(),
                entity.getLine1(),
                entity.getLine2(),
                entity.getCity(),
                entity.getLandmark(),
                entity.getArea(),
                entity.getPostOffice(),
                entity.getStateId() != null ? entity.getStateId().getId() : null,
                entity.getCountryId() != null ? entity.getCountryId().getId() : null,
                entity.getPinCode(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getIsPrimary(),
                entity.getAddressableId(),
                entity.getAddressableType(),
                entity.getActive()
        );
    }

    public static AddressEntity addressEntity(CreateAddressDTO dto, StateEntity state, CountryEntity country) {
        AddressEntity entity = new AddressEntity();
        entity.setAddressType(dto.addressType());
        entity.setLine1(dto.line1());
        entity.setLine2(dto.line2());
        entity.setCity(dto.city());
        entity.setLandmark(dto.landmark());
        entity.setArea(dto.area());
        entity.setPostOffice(dto.postOffice());
        entity.setStateId(state);
        entity.setCountryId(country);
        entity.setPinCode(dto.pinCode());
        entity.setLatitude(dto.latitude());
        entity.setLongitude(dto.longitude());
        entity.setIsPrimary(dto.isPrimary() != null ? dto.isPrimary() : false);
        entity.setActive(dto.active() != null ? dto.active() : true);
        return entity;
    }
}
