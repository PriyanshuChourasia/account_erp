package com.codymitra.shared_service.modules.country.mappers;

import com.codymitra.shared_service.modules.country.dtos.CountryDTO;
import com.codymitra.shared_service.modules.country.dtos.CreateCountryDTO;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;

public final class CountryMapper {

    public static CountryDTO countryDTO(CountryEntity country) {
        return new CountryDTO(
                country.getId(),
                country.getName(),
                country.getAlias(),
                country.getIso2Code(),
                country.getIso3Code(),
                country.getNumericCode(),
                country.getPhoneCode(),
                country.getCurrencyCode(),
                country.getCurrencyName(),
                country.getRegion(),
                country.getSubRegion(),
                country.getActive()
        );
    }

    public static CountryEntity countryEntity(CreateCountryDTO request) {
        CountryEntity country = new CountryEntity();
        country.setName(request.name());
        country.setAlias(request.alias());
        country.setIso2Code(request.iso2Code().toUpperCase());
        country.setIso3Code(request.iso3Code().toUpperCase());
        country.setNumericCode(request.numericCode());
        country.setPhoneCode(request.phoneCode());
        country.setCurrencyCode(request.currencyCode().toUpperCase());
        country.setCurrencyName(request.currencyName());
        country.setRegion(request.region());
        country.setSubRegion(request.subRegion());
        country.setActive(true);
        return country;
    }
}
