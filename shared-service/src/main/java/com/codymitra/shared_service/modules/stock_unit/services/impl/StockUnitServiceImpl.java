package com.codymitra.shared_service.modules.stock_unit.services.impl;


import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;
import com.codymitra.shared_service.modules.stock_unit.entities.StockUnitEntity;
import com.codymitra.shared_service.modules.stock_unit.mappers.StockUnitMapper;
import com.codymitra.shared_service.modules.stock_unit.repositories.StockUnitRepository;
import com.codymitra.shared_service.modules.stock_unit.services.StockUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockUnitServiceImpl implements StockUnitService {

    private final StockUnitRepository stockUnitRepository;

    @Override
    public StockUnitDTO  create(CreateStockUnitRequestDTO createStockUnitRequestDTO){

        if(stockUnitRepository.existsByName(createStockUnitRequestDTO.name())){
            throw new DataAlreadyExistsException("Unit already exists");
        }

        StockUnitEntity unitEntity = StockUnitMapper.stockUnitEntity(createStockUnitRequestDTO);
        StockUnitEntity createUnit = stockUnitRepository.save(unitEntity);

        StockUnitEntity baseUnit1DTO = new StockUnitEntity();
        StockUnitEntity baseUnit2DTO = new StockUnitEntity();

        if(createStockUnitRequestDTO.primaryUnitId() != null){
            baseUnit1DTO = stockUnitRepository.findById(createStockUnitRequestDTO.primaryUnitId()).orElseThrow(
                    () -> new DataNotFoundException("No such base unit found")
            );
        }

        if(createStockUnitRequestDTO.secondaryUnitId() != null){
            baseUnit2DTO = stockUnitRepository.findById(createStockUnitRequestDTO.secondaryUnitId()).orElseThrow(
                    () -> new DataNotFoundException("No such base unit found")
            );
        }

        return StockUnitMapper.stockUnitDTO(createUnit,baseUnit1DTO,baseUnit2DTO);
    }
}
