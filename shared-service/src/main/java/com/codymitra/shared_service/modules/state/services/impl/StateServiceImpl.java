package com.codymitra.shared_service.modules.state.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.country.repositories.CountryRepository;
import com.codymitra.shared_service.modules.state.dtos.CreateStateDTO;
import com.codymitra.shared_service.modules.state.dtos.StateDTO;
import com.codymitra.shared_service.modules.state.entities.StateEntity;
import com.codymitra.shared_service.modules.state.mappers.StateMapper;
import com.codymitra.shared_service.modules.state.repositories.StateRepository;
import com.codymitra.shared_service.modules.state.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;


    @Override
    public List<StateDTO> getAllStates(){
        List<StateEntity> stateEntities = stateRepository.findAll();
        return stateEntities.stream().map(StateMapper::stateDTO).toList();
    }

    @Override
    public StateDTO create(CreateStateDTO createStateDTO){
        if(stateRepository.existsByCode(createStateDTO.code())){
            throw new DataAlreadyExistsException("State already exists");
        }

        CountryEntity country = countryRepository.findById(createStateDTO.countryId()).orElseThrow(
                () -> new DataNotFoundException("No such country exists")
        );

        StateEntity createEntity = stateRepository.save(StateMapper.stateEntity(createStateDTO,country)) ;
        return StateMapper.stateDTO(createEntity);
    }
}
