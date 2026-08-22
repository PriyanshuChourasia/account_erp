package com.codymitra.shared_service.modules.currency_numbering_units.services;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CreateCurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.entities.CurrencyNumberingUnitEntity;

public interface CurrencyNumberingUnitService {

    List<CurrencyNumberingUnitDTO> getAll();

    CurrencyNumberingUnitDTO getById(UUID id);

    CurrencyNumberingUnitEntity getEntityById(UUID id);

    CurrencyNumberingUnitDTO create(CreateCurrencyNumberingUnitDTO request);

    CurrencyNumberingUnitDTO update(UUID id, CreateCurrencyNumberingUnitDTO request);

    String delete(UUID id);
}
