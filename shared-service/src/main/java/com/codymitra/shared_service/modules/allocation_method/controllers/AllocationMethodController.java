package com.codymitra.shared_service.modules.allocation_method.controllers;

import com.codymitra.shared_service.modules.allocation_method.dtos.AllocationMethodDTO;
import com.codymitra.shared_service.modules.allocation_method.dtos.CreateAllocationMethodRequest;
import com.codymitra.shared_service.modules.allocation_method.services.AllocationMethodService;
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
@RequestMapping(path = "/allocation-methods")
@RequiredArgsConstructor
public class AllocationMethodController {

    private final AllocationMethodService allocationMethodService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<AllocationMethodDTO> dtos = allocationMethodService.getAll();
        return ResponseHandler.generateResponse(dtos, "Allocation methods retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        AllocationMethodDTO dto = allocationMethodService.getById(id);
        return ResponseHandler.generateResponse(dto, "Allocation method retrieved successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateAllocationMethodRequest request) {
        String msg = allocationMethodService.create(request);
        return ResponseHandler.generateResponse(msg, HttpStatus.CREATED);
    }
}
