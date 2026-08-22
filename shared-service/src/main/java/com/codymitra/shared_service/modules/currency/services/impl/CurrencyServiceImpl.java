package com.codymitra.shared_service.modules.currency.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.currency.dtos.CreateCurrencyDTO;
import com.codymitra.shared_service.modules.currency.dtos.CurrencyDTO;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import com.codymitra.shared_service.modules.currency.mappers.CurrencyMapper;
import com.codymitra.shared_service.modules.currency.repositories.CurrencyRepository;
import com.codymitra.shared_service.modules.currency.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<CurrencyDTO> getAll() {
        return currencyRepository.findAll().stream().map(CurrencyMapper::currencyDTO).toList();
    }

    @Override
    public CurrencyEntity getEntityById(UUID id) {
        return currencyRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such currency found")
        );
    }

    @Override
    public CurrencyDTO create(CreateCurrencyDTO request) {
        if (currencyRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Currency already exists with this name");
        }
        if (currencyRepository.existsByCurrencySymbol(request.currencySymbol())) {
            throw new DataAlreadyExistsException("Currency already exists with this symbol");
        }
        CurrencyEntity entity = CurrencyMapper.currencyEntity(request);
        CurrencyEntity saved = currencyRepository.save(entity);
        return CurrencyMapper.currencyDTO(saved);
    }
}
