package com.codymitra.shared_service.modules.designation.controllers;

import com.codymitra.shared_service.modules.designation.dtos.CreateDesignationRequest;
import com.codymitra.shared_service.modules.designation.dtos.DesignationDTO;
import com.codymitra.shared_service.modules.designation.dtos.DesignationHierarchyDTO;
import com.codymitra.shared_service.modules.designation.services.DesignationService;
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
@RequestMapping(path = "/designations")
@RequiredArgsConstructor
public class DesignationController {

    private final DesignationService designationService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<DesignationDTO> dtos = designationService.getAll();
        String message = dtos.size() + " total designations fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/all-designation-tree")
    public ResponseEntity<Map<String, Object>> getAllTree() {
        List<DesignationHierarchyDTO> dtos = designationService.getAllWithChildren();
        String message = dtos.size() + " total root designations fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        DesignationDTO dto = designationService.getById(id);
        return ResponseHandler.generateResponse(dto, "Designation fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDesignationRequest request) {
        DesignationDTO dto = designationService.create(request);
        return ResponseHandler.generateResponse(dto, "Designation created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateDesignationRequest request) {
        DesignationDTO dto = designationService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Designation updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = designationService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
