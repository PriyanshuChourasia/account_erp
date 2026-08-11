package com.codymitra.shared_service.modules.country.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CountryDTO(
        Long id,
        String name,
        String alias,
        String iso2Code,
        String iso3Code,
        String numericCode,
        String phoneCode,
        String currencyCode,
        String currencyName,
        String region,
        String subRegion,
        Boolean active
) {
}
