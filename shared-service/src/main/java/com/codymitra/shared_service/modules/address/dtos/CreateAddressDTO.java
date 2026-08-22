package com.codymitra.shared_service.modules.address.dtos;

import com.codymitra.shared_service.modules.address.enums.AddressTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateAddressDTO(
        @NotNull(message = "Address type is required")
        AddressTypeEnum addressType,
        @NotBlank(message = "Line 1 is required")
        String line1,
        String line2,
        String city,
        String landmark,
        String area,
        String postOffice,
        @NotNull(message = "State is required")
        UUID stateId,
        @NotNull(message = "Country is required")
        UUID countryId,
        @NotBlank(message = "Pin code is required")
        @Size(min = 3, max = 10, message = "PinCode cannot be less than 3 and more than 10")
        String pinCode,
        String latitude,
        String longitude,
        Boolean isPrimary,
        Boolean active
) {}
