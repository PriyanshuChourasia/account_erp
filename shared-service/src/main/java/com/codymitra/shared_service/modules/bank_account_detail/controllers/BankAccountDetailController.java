package com.codymitra.shared_service.modules.bank_account_detail.controllers;

import com.codymitra.shared_service.modules.bank_account_detail.dtos.BankAccountDetailDTO;
import com.codymitra.shared_service.modules.bank_account_detail.dtos.CreateBankAccountDetailRequest;
import com.codymitra.shared_service.modules.bank_account_detail.services.BankAccountDetailService;
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
@RequestMapping(path = "/bank-account-details")
@RequiredArgsConstructor
public class BankAccountDetailController {

    private final BankAccountDetailService bankAccountDetailService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<BankAccountDetailDTO> dtos = bankAccountDetailService.getAll();
        return ResponseHandler.generateResponse(dtos, "Bank account details retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        BankAccountDetailDTO dto = bankAccountDetailService.getById(id);
        return ResponseHandler.generateResponse(dto, "Bank account detail retrieved successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateBankAccountDetailRequest request) {
        String msg = bankAccountDetailService.create(request);
        return ResponseHandler.generateResponse(msg, HttpStatus.CREATED);
    }
}