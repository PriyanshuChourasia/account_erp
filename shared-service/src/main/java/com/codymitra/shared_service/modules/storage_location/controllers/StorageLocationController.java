package com.codymitra.shared_service.modules.storage_location.controllers;

import java.util.UUID;
import com.codymitra.shared_service.modules.storage_location.dtos.CreateStorageLocationRequestDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationHierarchyDTO;
import com.codymitra.shared_service.modules.storage_location.services.StorageLocationService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/storage_locations")
@RequiredArgsConstructor
public class StorageLocationController {

    private final StorageLocationService storageLocationService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<StorageLocationDTO> dtos = storageLocationService.getAll();
        String message = dtos.size() + " total storage locations fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/tree")
    public ResponseEntity<Map<String, Object>> getAllWithChildren() {
        List<StorageLocationHierarchyDTO> dtos = storageLocationService.getAllWithChildren();
        String message = dtos.size() + " total storage locations fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        StorageLocationDTO dto = storageLocationService.getById(id);
        return ResponseHandler.generateResponse(dto, "Storage location fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateStorageLocationRequestDTO request) {
        StorageLocationDTO dto = storageLocationService.create(request);
        return ResponseHandler.generateResponse(dto, "Storage location created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateStorageLocationRequestDTO request) {
        StorageLocationDTO dto = storageLocationService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Storage location updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = storageLocationService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
