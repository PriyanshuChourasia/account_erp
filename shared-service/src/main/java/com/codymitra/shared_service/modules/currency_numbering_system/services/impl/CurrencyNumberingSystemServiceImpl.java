package com.codymitra.shared_service.modules.currency_numbering_system.services.impl;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CreateCurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import com.codymitra.shared_service.modules.currency_numbering_system.mappers.CurrencyNumberingSystemMapper;
import com.codymitra.shared_service.modules.currency_numbering_system.repositories.CurrencyNumberingSystemRepository;
import com.codymitra.shared_service.modules.currency_numbering_system.services.CurrencyNumberingSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurrencyNumberingSystemServiceImpl implements CurrencyNumberingSystemService {

    private final CurrencyNumberingSystemRepository currencyNumberingSystemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyNumberingSystemDTO> getAll() {
        return currencyNumberingSystemRepository.findAll().stream()
                .map(CurrencyNumberingSystemMapper::systemDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyNumberingSystemDTO getById(UUID id) {
        return CurrencyNumberingSystemMapper.systemDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyNumberingSystemEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public CurrencyNumberingSystemDTO create(CreateCurrencyNumberingSystemDTO request) {
        validateUnique(request.name(), request.code(), null);
        CurrencyNumberingSystemEntity saved = currencyNumberingSystemRepository.save(
                CurrencyNumberingSystemMapper.systemEntity(request)
        );
        return CurrencyNumberingSystemMapper.systemDTO(saved);
    }

    @Override
    @Transactional
    public CurrencyNumberingSystemDTO update(UUID id, CreateCurrencyNumberingSystemDTO request) {
        findById(id);
        validateUnique(request.name(), request.code(), id);
        CurrencyNumberingSystemEntity updated = currencyNumberingSystemRepository.save(
                CurrencyNumberingSystemMapper.systemEntity(findById(id), request)
        );
        return CurrencyNumberingSystemMapper.systemDTO(updated);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        currencyNumberingSystemRepository.delete(findById(id));
        return "Currency numbering system deleted successfully";
    }

    private void validateUnique(String name, String code, UUID id) {
        if (currencyNumberingSystemRepository.existsByName(name)) {
            throw new DataAlreadyExistsException("Currency numbering system already exists with this name");
        }
        if (id != null && currencyNumberingSystemRepository.existsByNameAndIdNot(name, id)) {
            throw new DataAlreadyExistsException("Currency numbering system already exists with this name");
        }
        if (currencyNumberingSystemRepository.existsByCode(code.toUpperCase())) {
            throw new DataAlreadyExistsException("Currency numbering system already exists with this code");
        }
        if (id != null && currencyNumberingSystemRepository.existsByCodeAndIdNot(code.toUpperCase(), id)) {
            throw new DataAlreadyExistsException("Currency numbering system already exists with this code");
        }
    }

    private CurrencyNumberingSystemEntity findById(UUID id) {
        return currencyNumberingSystemRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such currency numbering system found")
        );
    }
}
