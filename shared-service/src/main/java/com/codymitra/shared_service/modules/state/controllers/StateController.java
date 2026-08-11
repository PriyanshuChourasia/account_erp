package com.codymitra.shared_service.modules.state.controllers;

import com.codymitra.shared_service.modules.state.dtos.CreateStateDTO;
import com.codymitra.shared_service.modules.state.dtos.StateDTO;
import com.codymitra.shared_service.modules.state.services.StateService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/states")
@RequiredArgsConstructor
public class StateController {


    private final StateService stateService;


    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllStates(){
        List<StateDTO> stateDTOS = stateService.getAllStates();
        return ResponseHandler.generateResponse(stateDTOS,"Reports fetched successfully", HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@Valid @RequestBody CreateStateDTO createStateDTO){
        StateDTO stateDTO = stateService.create(createStateDTO);
        return ResponseHandler.generateResponse(stateDTO,"State created successfully",HttpStatus.CREATED);
    }

}
