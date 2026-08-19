package com.codymitra.shared_service.modules.unique_quantity_code.controllers;

import java.util.UUID;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.CreateUniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.UniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.services.UniqueQuantityCodeService;
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
public class UniqueQuantityCodeController {

    private final UniqueQuantityCodeService uqcService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<UniqueQuantityCodeDTO> uqcdtos = uqcService.getAll();
        String message = uqcdtos.size() + " total UQCs fetched";
        return ResponseHandler.generateResponse(uqcdtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        UniqueQuantityCodeDTO dto = uqcService.getById(id);
        return ResponseHandler.generateResponse(dto, "UQC fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateUniqueQuantityCodeDTO request) {
        UniqueQuantityCodeDTO dto = uqcService.create(request);
        return ResponseHandler.generateResponse(dto, "UQC created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateUniqueQuantityCodeDTO request) {
        UniqueQuantityCodeDTO dto = uqcService.update(id, request);
        return ResponseHandler.generateResponse(dto, "UQC updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = uqcService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
