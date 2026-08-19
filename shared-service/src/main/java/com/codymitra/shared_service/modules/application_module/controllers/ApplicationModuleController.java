package com.codymitra.shared_service.modules.application_module.controllers;


import com.codymitra.shared_service.modules.application_module.dtos.ApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.dtos.CreateApplicationModuleDTO;
import com.codymitra.shared_service.modules.application_module.services.ApplicationModuleService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/application_modules")
@RequiredArgsConstructor
public class ApplicationModuleController {

    private final ApplicationModuleService applicationModuleService;


    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllApplicationModules(){
        List<ApplicationModuleDTO> applicationModuleDTOS = applicationModuleService.getAllApplicationModules();
        String message = applicationModuleDTOS.size()+" application module fetched successfully";
        return ResponseHandler.generateResponse(applicationModuleDTOS,message, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createApplicationModule(@Valid @RequestBody CreateApplicationModuleDTO createApplicationModuleDTO){
        String message = applicationModuleService.create(createApplicationModuleDTO);
        return ResponseHandler.generateResponse(message,HttpStatus.CREATED);
    }

}
