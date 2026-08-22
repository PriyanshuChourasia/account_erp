package com.codymitra.shared_service.modules.currency_numbering_units.services.impl;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import com.codymitra.shared_service.modules.currency_numbering_system.services.CurrencyNumberingSystemService;
import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CreateCurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.entities.CurrencyNumberingUnitEntity;
import com.codymitra.shared_service.modules.currency_numbering_units.mappers.CurrencyNumberingUnitMapper;
import com.codymitra.shared_service.modules.currency_numbering_units.repositories.CurrencyNumberingUnitRepository;
import com.codymitra.shared_service.modules.currency_numbering_units.services.CurrencyNumberingUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurrencyNumberingUnitServiceImpl implements CurrencyNumberingUnitService {

    private final CurrencyNumberingUnitRepository currencyNumberingUnitRepository;
    private final CurrencyNumberingSystemService currencyNumberingSystemService;

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyNumberingUnitDTO> getAll() {
        return currencyNumberingUnitRepository.findAll().stream()
                .map(CurrencyNumberingUnitMapper::unitDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyNumberingUnitDTO getById(UUID id) {
        return CurrencyNumberingUnitMapper.unitDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyNumberingUnitEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public CurrencyNumberingUnitDTO create(CreateCurrencyNumberingUnitDTO request) {
        CurrencyNumberingSystemEntity system = currencyNumberingSystemService.getEntityById(request.numberingSystemId());

        if (currencyNumberingUnitRepository.existsByNameAndNumberingSystemId(request.name(), system.getId())) {
            throw new DataAlreadyExistsException("Currency numbering unit already exists in this numbering system");
        }

        Integer sequence = request.sequence() != null ? request.sequence() : nextSequence(system.getId());
        CurrencyNumberingUnitEntity saved = currencyNumberingUnitRepository.save(
                CurrencyNumberingUnitMapper.unitEntity(request, system, sequence)
        );
        return CurrencyNumberingUnitMapper.unitDTO(saved);
    }

    @Override
    @Transactional
    public CurrencyNumberingUnitDTO update(UUID id, CreateCurrencyNumberingUnitDTO request) {
        CurrencyNumberingUnitEntity entity = findById(id);
        CurrencyNumberingSystemEntity system = currencyNumberingSystemService.getEntityById(request.numberingSystemId());

        if (currencyNumberingUnitRepository.existsByNameAndNumberingSystemIdAndIdNot(request.name(), system.getId(), id)) {
            throw new DataAlreadyExistsException("Currency numbering unit already exists in this numbering system");
        }

        CurrencyNumberingUnitEntity updated = currencyNumberingUnitRepository.save(
                CurrencyNumberingUnitMapper.unitEntity(entity, request, system)
        );
        return CurrencyNumberingUnitMapper.unitDTO(updated);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        currencyNumberingUnitRepository.delete(findById(id));
        return "Currency numbering unit deleted successfully";
    }

    private CurrencyNumberingUnitEntity findById(UUID id) {
        return currencyNumberingUnitRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such currency numbering unit found")
        );
    }

    private Integer nextSequence(UUID numberingSystemId) {
        return currencyNumberingUnitRepository.findTopByNumberingSystemIdOrderBySequenceDesc(numberingSystemId)
                .map(unit -> unit.getSequence() != null ? unit.getSequence() + 1 : 1)
                .orElse(1);
    }
}
