package com.codymitra.shared_service.modules.currency_minor_unit.services;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CreateCurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.entities.CurrencyMinorUnitEntity;

public interface CurrencyMinorUnitService {

    List<CurrencyMinorUnitDTO> getAll();

    CurrencyMinorUnitDTO getById(UUID id);

    CurrencyMinorUnitEntity getEntityById(UUID id);

    CurrencyMinorUnitDTO create(CreateCurrencyMinorUnitDTO request);

    CurrencyMinorUnitDTO update(UUID id, CreateCurrencyMinorUnitDTO request);

    String delete(UUID id);
}
