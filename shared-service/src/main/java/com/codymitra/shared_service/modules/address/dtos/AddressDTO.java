package com.codymitra.shared_service.modules.address.dtos;

import com.codymitra.shared_service.modules.address.enums.AddressTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressDTO(
        UUID id,
        AddressTypeEnum addressType,
        String line1,
        String line2,
        String city,
        String landmark,
        String area,
        String postOffice,
        UUID stateId,
        UUID countryId,
        String pinCode,
        String latitude,
        String longitude,
        Boolean isPrimary,
        String addressableId,
        String addressableType,
        Boolean active
) {}
