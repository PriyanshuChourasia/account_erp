package com.codymitra.shared_service.modules.country.controllers;

import com.codymitra.shared_service.modules.country.dtos.CountryDTO;
import com.codymitra.shared_service.modules.country.dtos.CreateCountryDTO;
import com.codymitra.shared_service.modules.country.services.CountryService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CountryDTO> dtos = countryService.getAll();
        String message = dtos.size() + " total countries fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateCountryDTO request) {
        CountryDTO dto = countryService.create(request);
        return ResponseHandler.generateResponse(dto, "Country created successfully", HttpStatus.CREATED);
    }
}
