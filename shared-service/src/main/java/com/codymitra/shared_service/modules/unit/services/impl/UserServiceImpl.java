package com.codymitra.shared_service.modules.unit.services.impl;


import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.unit.dtos.CreateUnitRequestDTO;
import com.codymitra.shared_service.modules.unit.dtos.UnitDTO;
import com.codymitra.shared_service.modules.unit.entities.UnitEntity;
import com.codymitra.shared_service.modules.unit.mappers.UnitMapper;
import com.codymitra.shared_service.modules.unit.repositories.UnitRepository;
import com.codymitra.shared_service.modules.unit.services.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    @Override
    public UnitDTO  create(CreateUnitRequestDTO createUnitRequestDTO){

        if(unitRepository.existsByName(createUnitRequestDTO.name())){
            throw new DataAlreadyExistsException("Unit already exists");
        }

        UnitEntity unitEntity = UnitMapper.unitEntity(createUnitRequestDTO);
        UnitEntity createUnit = unitRepository.save(unitEntity);

        UnitEntity baseUnit1DTO = new UnitEntity();
        UnitEntity baseUnit2DTO = new UnitEntity();

        if(createUnitRequestDTO.primaryUnitId() != null){
            baseUnit1DTO = unitRepository.findById(createUnitRequestDTO.primaryUnitId()).orElseThrow(
                    () -> new DataNotFoundException("No such base unit found")
            );
        }

        if(createUnitRequestDTO.secondaryUnitId() != null){
            baseUnit2DTO = unitRepository.findById(createUnitRequestDTO.secondaryUnitId()).orElseThrow(
                    () -> new DataNotFoundException("No such base unit found")
            );
        }

        return UnitMapper.unitDTO(createUnit,baseUnit1DTO,baseUnit2DTO);
    }
}
