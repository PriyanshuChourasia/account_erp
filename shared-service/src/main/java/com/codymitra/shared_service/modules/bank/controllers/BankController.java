package com.codymitra.shared_service.modules.bank.controllers;

import com.codymitra.shared_service.modules.bank.dtos.BankDTO;
import com.codymitra.shared_service.modules.bank.dtos.CreateBankRequest;
import com.codymitra.shared_service.modules.bank.services.BankService;
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
@RequestMapping(path = "/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<BankDTO> dtos = bankService.getAll();
        return ResponseHandler.generateResponse(dtos, "Banks retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        BankDTO dto = bankService.getById(id);
        return ResponseHandler.generateResponse(dto, "Bank retrieved successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateBankRequest request) {
        String msg = bankService.create(request);
        return ResponseHandler.generateResponse(msg, HttpStatus.CREATED);
    }
}