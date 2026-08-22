package com.codymitra.shared_service.modules.currency.services;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency.dtos.CreateCurrencyDTO;
import com.codymitra.shared_service.modules.currency.dtos.CurrencyDTO;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;

public interface CurrencyService {

    List<CurrencyDTO> getAll();

    CurrencyEntity getEntityById(UUID id);

    CurrencyDTO create(CreateCurrencyDTO request);
}
