package com.codymitra.shared_service.modules.application_feature.controllers;


import com.codymitra.shared_service.modules.application_feature.dtos.ApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.dtos.CreateApplicationFeatureDTO;
import com.codymitra.shared_service.modules.application_feature.services.ApplicationFeatureService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/application_features")
@RequiredArgsConstructor
public class ApplicationFeatureController {

    private final ApplicationFeatureService applicationFeatureService;


    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllApplicationFeatures(){
        List<ApplicationFeatureDTO> applicationFeatureDTOS = applicationFeatureService.getAllApplicationFeatures();
        String message = applicationFeatureDTOS.size()+" application feature fetched successfully";
        return ResponseHandler.generateResponse(applicationFeatureDTOS,message, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createApplicationFeature(@Valid @RequestBody CreateApplicationFeatureDTO createApplicationFeatureDTO){
        String message = applicationFeatureService.create(createApplicationFeatureDTO);
        return ResponseHandler.generateResponse(message,HttpStatus.CREATED);
    }

}
