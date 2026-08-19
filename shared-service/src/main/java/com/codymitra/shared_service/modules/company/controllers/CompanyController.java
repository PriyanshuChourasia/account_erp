package com.codymitra.shared_service.modules.company.controllers;

import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.services.CompanyService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllCompanies(){
        List<CompanyDTO> companyDTOS = companyService.getAllCompanies();
        String message = companyDTOS.size()+" companies fetched successfully";
        return ResponseHandler.generateResponse(companyDTOS, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createCompany(@Valid @RequestBody CreateCompanyDTO createCompanyDTO){
        String message = companyService.create(createCompanyDTO);
        return ResponseHandler.generateResponse(message, HttpStatus.CREATED);
    }
}
