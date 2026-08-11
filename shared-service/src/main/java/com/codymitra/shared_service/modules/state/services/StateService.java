package com.codymitra.shared_service.modules.state.services;

import com.codymitra.shared_service.modules.state.dtos.CreateStateDTO;
import com.codymitra.shared_service.modules.state.dtos.StateDTO;

import java.util.List;

public interface StateService {

    List<StateDTO> getAllStates();
    StateDTO create(CreateStateDTO createStateDTO);
}
