package com.codymitra.shared_service.modules.currency.services;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency.dtos.CreateCurrencyDTO;
import com.codymitra.shared_service.modules.currency.dtos.CurrencyDTO;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;

public interface CurrencyService {

    List<CurrencyDTO> getAll();

    CurrencyDTO getById(UUID id);

    CurrencyEntity getEntityById(UUID id);

    CurrencyDTO create(CreateCurrencyDTO request);

    CurrencyDTO update(UUID id, CreateCurrencyDTO request);

    String delete(UUID id);
}
