package com.codymitra.shared_service.modules.uqc.controllers;

import com.codymitra.shared_service.modules.uqc.dtos.CreateUQCDTO;
import com.codymitra.shared_service.modules.uqc.dtos.UQCDTO;
import com.codymitra.shared_service.modules.uqc.services.UQCService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/unique_quantity_codes")
@RequiredArgsConstructor
public class UQCController {

    private final UQCService uqcService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<UQCDTO> uqcdtos = uqcService.getAll();
        String message = uqcdtos.size() + " total UQCs fetched";
        return ResponseHandler.generateResponse(uqcdtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        UQCDTO dto = uqcService.getById(id);
        return ResponseHandler.generateResponse(dto, "UQC fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateUQCDTO request) {
        UQCDTO dto = uqcService.create(request);
        return ResponseHandler.generateResponse(dto, "UQC created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody CreateUQCDTO request) {
        UQCDTO dto = uqcService.update(id, request);
        return ResponseHandler.generateResponse(dto, "UQC updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        String message = uqcService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
