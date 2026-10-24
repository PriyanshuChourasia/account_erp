package com.codymitra.shared_service.modules.blood_group.controllers;

import com.codymitra.shared_service.modules.blood_group.dtos.BloodGroupDTO;
import com.codymitra.shared_service.modules.blood_group.dtos.CreateBloodGroupRequest;
import com.codymitra.shared_service.modules.blood_group.services.BloodGroupService;
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
@RequestMapping(path = "/blood-groups")
@RequiredArgsConstructor
public class BloodGroupController {

    private final BloodGroupService bloodGroupService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<BloodGroupDTO> dtos = bloodGroupService.getAll();
        return ResponseHandler.generateResponse(dtos, "Blood groups retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        BloodGroupDTO dto = bloodGroupService.getById(id);
        return ResponseHandler.generateResponse(dto, "Blood group retrieved successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateBloodGroupRequest request) {
        String msg = bloodGroupService.create(request);
        return ResponseHandler.generateResponse(msg, HttpStatus.CREATED);
    }
}
