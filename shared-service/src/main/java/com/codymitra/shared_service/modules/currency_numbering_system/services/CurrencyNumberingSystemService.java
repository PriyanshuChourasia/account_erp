package com.codymitra.shared_service.modules.currency_numbering_system.services;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CreateCurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;

public interface CurrencyNumberingSystemService {

    List<CurrencyNumberingSystemDTO> getAll();

    CurrencyNumberingSystemDTO getById(UUID id);

    CurrencyNumberingSystemEntity getEntityById(UUID id);

    CurrencyNumberingSystemDTO create(CreateCurrencyNumberingSystemDTO request);

    CurrencyNumberingSystemDTO update(UUID id, CreateCurrencyNumberingSystemDTO request);

    String delete(UUID id);
}
