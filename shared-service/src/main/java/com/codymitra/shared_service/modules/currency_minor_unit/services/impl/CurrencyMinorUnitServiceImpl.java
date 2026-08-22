package com.codymitra.shared_service.modules.currency_minor_unit.services.impl;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import com.codymitra.shared_service.modules.currency.services.CurrencyService;
import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CreateCurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.entities.CurrencyMinorUnitEntity;
import com.codymitra.shared_service.modules.currency_minor_unit.mappers.CurrencyMinorUnitMapper;
import com.codymitra.shared_service.modules.currency_minor_unit.repositories.CurrencyMinorUnitRepository;
import com.codymitra.shared_service.modules.currency_minor_unit.services.CurrencyMinorUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurrencyMinorUnitServiceImpl implements CurrencyMinorUnitService {

    private final CurrencyMinorUnitRepository currencyMinorUnitRepository;
    private final CurrencyService currencyService;

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyMinorUnitDTO> getAll() {
        return currencyMinorUnitRepository.findAll().stream()
                .map(CurrencyMinorUnitMapper::minorUnitDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyMinorUnitDTO getById(UUID id) {
        return CurrencyMinorUnitMapper.minorUnitDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyMinorUnitEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public CurrencyMinorUnitDTO create(CreateCurrencyMinorUnitDTO request) {
        CurrencyEntity currency = currencyService.getEntityById(request.currencyId());

        if (currencyMinorUnitRepository.existsByNameAndCurrencyId(request.name(), currency.getId())) {
            throw new DataAlreadyExistsException("Currency minor unit already exists for this currency");
        }

        CurrencyMinorUnitEntity saved = currencyMinorUnitRepository.save(
                CurrencyMinorUnitMapper.minorUnitEntity(request, currency)
        );
        return CurrencyMinorUnitMapper.minorUnitDTO(saved);
    }

    @Override
    @Transactional
    public CurrencyMinorUnitDTO update(UUID id, CreateCurrencyMinorUnitDTO request) {
        findById(id);
        CurrencyEntity currency = currencyService.getEntityById(request.currencyId());

        if (currencyMinorUnitRepository.existsByNameAndCurrencyIdAndIdNot(request.name(), currency.getId(), id)) {
            throw new DataAlreadyExistsException("Currency minor unit already exists for this currency");
        }

        CurrencyMinorUnitEntity updated = currencyMinorUnitRepository.save(
                CurrencyMinorUnitMapper.minorUnitEntity(findById(id), request, currency)
        );
        return CurrencyMinorUnitMapper.minorUnitDTO(updated);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        currencyMinorUnitRepository.delete(findById(id));
        return "Currency minor unit deleted successfully";
    }

    private CurrencyMinorUnitEntity findById(UUID id) {
        return currencyMinorUnitRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such currency minor unit found")
        );
    }
}
