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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyDTO> getAll() {
        return currencyRepository.findAll().stream().map(CurrencyMapper::currencyDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyDTO getById(UUID id) {
        return CurrencyMapper.currencyDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public CurrencyDTO create(CreateCurrencyDTO request) {
        validateUnique(request.name(), request.currencySymbol(), null);
        CurrencyEntity saved = currencyRepository.save(CurrencyMapper.currencyEntity(request));
        return CurrencyMapper.currencyDTO(saved);
    }

    @Override
    @Transactional
    public CurrencyDTO update(UUID id, CreateCurrencyDTO request) {
        findById(id);
        validateUnique(request.name(), request.currencySymbol(), id);
        CurrencyEntity updated = currencyRepository.save(
                CurrencyMapper.currencyEntity(findById(id), request)
        );
        return CurrencyMapper.currencyDTO(updated);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        currencyRepository.delete(findById(id));
        return "Currency deleted successfully";
    }

    private void validateUnique(String name, String currencySymbol, UUID id) {
        if (currencyRepository.existsByName(name)) {
            throw new DataAlreadyExistsException("Currency already exists with this name");
        }
        if (id != null && currencyRepository.existsByNameAndIdNot(name, id)) {
            throw new DataAlreadyExistsException("Currency already exists with this name");
        }
        if (currencyRepository.existsByCurrencySymbol(currencySymbol)) {
            throw new DataAlreadyExistsException("Currency already exists with this symbol");
        }
        if (id != null && currencyRepository.existsByCurrencySymbolAndIdNot(currencySymbol, id)) {
            throw new DataAlreadyExistsException("Currency already exists with this symbol");
        }
    }

    private CurrencyEntity findById(UUID id) {
        return currencyRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such currency found")
        );
    }
}
