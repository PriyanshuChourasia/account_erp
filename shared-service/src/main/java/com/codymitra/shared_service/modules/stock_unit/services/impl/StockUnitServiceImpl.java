package com.codymitra.shared_service.modules.stock_unit.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;
import com.codymitra.shared_service.modules.stock_unit.entities.StockUnitEntity;
import com.codymitra.shared_service.modules.stock_unit.mappers.StockUnitMapper;
import com.codymitra.shared_service.modules.stock_unit.repositories.StockUnitRepository;
import com.codymitra.shared_service.modules.stock_unit.services.StockUnitService;
import com.codymitra.shared_service.modules.unique_quantity_code.entities.UniqueQuantityCodeEntity;
import com.codymitra.shared_service.modules.unique_quantity_code.repositories.UniqueQuantityCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockUnitServiceImpl implements StockUnitService {

    private final StockUnitRepository stockUnitRepository;
    private final UniqueQuantityCodeRepository uqcRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StockUnitDTO> getAll() {
        return stockUnitRepository.findAll().stream()
                .map(unit -> StockUnitMapper.stockUnitDTO(unit, resolveUnit(unit.getPrimaryUnitId()), resolveUnit(unit.getSecondaryUnitId())))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StockUnitDTO getById(UUID id) {
        StockUnitEntity unit = findById(id);
        return StockUnitMapper.stockUnitDTO(unit, resolveUnit(unit.getPrimaryUnitId()), resolveUnit(unit.getSecondaryUnitId()));
    }

    @Override
    @Transactional(readOnly = true)
    public StockUnitEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public StockUnitDTO create(CreateStockUnitRequestDTO createStockUnitRequestDTO) {

        if (stockUnitRepository.existsByName(createStockUnitRequestDTO.name())) {
            throw new DataAlreadyExistsException("Unit already exists");
        }

        UniqueQuantityCodeEntity uqc = resolveUqc(createStockUnitRequestDTO.uqcId());
        StockUnitEntity unitEntity = StockUnitMapper.stockUnitEntity(createStockUnitRequestDTO, uqc);
        StockUnitEntity createUnit = stockUnitRepository.save(unitEntity);

        StockUnitEntity baseUnit1 = resolveRequiredUnit(createStockUnitRequestDTO.primaryUnitId());
        StockUnitEntity baseUnit2 = resolveRequiredUnit(createStockUnitRequestDTO.secondaryUnitId());

        return StockUnitMapper.stockUnitDTO(createUnit, baseUnit1, baseUnit2);
    }

    @Override
    @Transactional
    public StockUnitDTO update(UUID id, CreateStockUnitRequestDTO createStockUnitRequestDTO) {
        StockUnitEntity unit = findById(id);

        if (stockUnitRepository.existsByNameAndIdNot(createStockUnitRequestDTO.name(), id)) {
            throw new DataAlreadyExistsException("Unit already exists");
        }

        UniqueQuantityCodeEntity uqc = resolveUqc(createStockUnitRequestDTO.uqcId());
        StockUnitEntity updatedUnit = StockUnitMapper.stockUnitEntity(unit, createStockUnitRequestDTO, uqc);
        StockUnitEntity savedUnit = stockUnitRepository.save(updatedUnit);

        StockUnitEntity baseUnit1 = resolveRequiredUnit(createStockUnitRequestDTO.primaryUnitId());
        StockUnitEntity baseUnit2 = resolveRequiredUnit(createStockUnitRequestDTO.secondaryUnitId());

        return StockUnitMapper.stockUnitDTO(savedUnit, baseUnit1, baseUnit2);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        stockUnitRepository.delete(findById(id));
        return "Unit deleted successfully";
    }

    private StockUnitEntity findById(UUID id) {
        return stockUnitRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such unit found")
        );
    }

    private StockUnitEntity resolveRequiredUnit(UUID id) {
        if (id == null) {
            return new StockUnitEntity();
        }
        return stockUnitRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such base unit found")
        );
    }

    private StockUnitEntity resolveUnit(UUID id) {
        if (id == null) {
            return new StockUnitEntity();
        }
        return stockUnitRepository.findById(id).orElse(new StockUnitEntity());
    }

    private UniqueQuantityCodeEntity resolveUqc(UUID id) {
        if (id == null) {
            return null;
        }
        return uqcRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such UQC found")
        );
    }
}
