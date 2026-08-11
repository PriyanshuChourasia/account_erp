package com.codymitra.shared_service.modules.country.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCountryDTO(
        @NotBlank(message = "Name is required")
        String name,
        String alias,
        @NotBlank(message = "ISO2 code is required")
        @Size(min = 2, max = 2, message = "ISO2 code must be exactly 2 characters")
        String iso2Code,
        @NotBlank(message = "ISO3 code is required")
        @Size(min = 3, max = 3, message = "ISO3 code must be exactly 3 characters")
        String iso3Code,
        @NotBlank(message = "Numeric code is required")
        @Size(min = 3, max = 3, message = "Numeric code must be exactly 3 characters")
        String numericCode,
        @NotBlank(message = "Phone code is required")
        String phoneCode,
        @NotBlank(message = "Currency code is required")
        @Size(min = 3, max = 3, message = "Currency code must be exactly 3 characters")
        String currencyCode,
        @NotBlank(message = "Currency name is required")
        String currencyName,
        String region,
        String subRegion
) {
}
