package com.codymitra.shared_service.modules.state.mappers;

import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.state.dtos.CreateStateDTO;
import com.codymitra.shared_service.modules.state.dtos.StateDTO;
import com.codymitra.shared_service.modules.state.entities.StateEntity;

public final class StateMapper {

    public static StateDTO stateDTO(StateEntity state){
        return new StateDTO(
                state.getId(),
                state.getName(),
                state.getCode(),
                state.getGstCode(),
                state.getDescription(),
                state.getActive()
        );
    }

    public static StateEntity stateEntity(CreateStateDTO createStateDTO, CountryEntity country){
        StateEntity stateEntity = new StateEntity();
        stateEntity.setCode(createStateDTO.code());
        stateEntity.setDescription(createStateDTO.description());
        stateEntity.setGstCode(createStateDTO.gstCode());
        stateEntity.setCountryId(country);
        stateEntity.setActive(true);
        return stateEntity;
    }
}
