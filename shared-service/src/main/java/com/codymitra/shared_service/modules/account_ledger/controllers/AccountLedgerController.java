package com.codymitra.shared_service.modules.account_ledger.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.codymitra.shared_service.modules.account_ledger.dtos.AccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.dtos.CreateAccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.services.AccountLedgerService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account_ledgers")
@RequiredArgsConstructor
public class AccountLedgerController {

    private final AccountLedgerService accountLedgerService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<AccountLedgerDTO> dtos = accountLedgerService.getAll();
        String message = dtos.size() + " total account ledgers fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        AccountLedgerDTO dto = accountLedgerService.getById(id);
        return ResponseHandler.generateResponse(dto, "Account Ledger fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateAccountLedgerDTO request) {
        AccountLedgerDTO dto = accountLedgerService.create(request);
        return ResponseHandler.generateResponse(dto, "Account Ledger created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateAccountLedgerDTO request) {
        AccountLedgerDTO dto = accountLedgerService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Account Ledger updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = accountLedgerService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
