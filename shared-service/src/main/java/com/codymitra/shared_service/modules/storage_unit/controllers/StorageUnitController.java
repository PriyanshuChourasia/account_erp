package com.codymitra.shared_service.modules.storage_unit.controllers;


import com.codymitra.shared_service.modules.storage_unit.dtos.StorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.dtos.CreateStorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.services.StorageUnitService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/storage_units")
@RequiredArgsConstructor
public class StorageUnitController {

    private final StorageUnitService storageUnitService;


    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllStorageUnits(){
        List<StorageUnitDTO> storageUnitDTOS = storageUnitService.getAllStorageUnits();
        String message = storageUnitDTOS.size()+" storage unit fetched successfully";
        return ResponseHandler.generateResponse(storageUnitDTOS,message, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createStorageUnit(@Valid @RequestBody CreateStorageUnitDTO createStorageUnitDTO){
        String message = storageUnitService.create(createStorageUnitDTO);
        return ResponseHandler.generateResponse(message,HttpStatus.CREATED);
    }

}
