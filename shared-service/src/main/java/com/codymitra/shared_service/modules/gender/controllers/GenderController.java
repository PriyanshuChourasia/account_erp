package com.codymitra.shared_service.modules.gender.controllers;

import com.codymitra.shared_service.modules.gender.dtos.CreateGenderRequest;
import com.codymitra.shared_service.modules.gender.dtos.GenderDTO;
import com.codymitra.shared_service.modules.gender.services.GenderService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/genders")
@RequiredArgsConstructor
public class GenderController {

    private final GenderService genderService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<GenderDTO> dtos = genderService.getAll();
        String message = dtos.size() + " total genders fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        GenderDTO dto = genderService.getById(id);
        return ResponseHandler.generateResponse(dto, "Gender fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateGenderRequest request) {
        GenderDTO dto = genderService.create(request);
        return ResponseHandler.generateResponse(dto, "Gender created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateGenderRequest request) {
        GenderDTO dto = genderService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Gender updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = genderService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
