package com.codymitra.shared_service.modules.bank_type.controllers;

import com.codymitra.shared_service.modules.bank_type.dtos.BankTypeDTO;
import com.codymitra.shared_service.modules.bank_type.dtos.CreateBankTypeRequest;
import com.codymitra.shared_service.modules.bank_type.services.BankTypeService;
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
@RequestMapping(path = "/bank-types")
@RequiredArgsConstructor
public class BankTypeController {

    private final BankTypeService bankTypeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<BankTypeDTO> dtos = bankTypeService.getAll();
        return ResponseHandler.generateResponse(dtos, "Bank types retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        BankTypeDTO dto = bankTypeService.getById(id);
        return ResponseHandler.generateResponse(dto, "Bank type retrieved successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateBankTypeRequest request) {
        String msg = bankTypeService.create(request);
        return ResponseHandler.generateResponse(msg, HttpStatus.CREATED);
    }
}