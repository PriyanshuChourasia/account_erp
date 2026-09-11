package com.codymitra.shared_service.modules.bank_branch.controllers;

import com.codymitra.shared_service.modules.bank_branch.dtos.BankBranchDTO;
import com.codymitra.shared_service.modules.bank_branch.dtos.CreateBankBranchRequest;
import com.codymitra.shared_service.modules.bank_branch.services.BankBranchService;
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
@RequestMapping(path = "/bank-branches")
@RequiredArgsConstructor
public class BankBranchController {

    private final BankBranchService bankBranchService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<BankBranchDTO> dtos = bankBranchService.getAll();
        return ResponseHandler.generateResponse(dtos, "Bank branches retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        BankBranchDTO dto = bankBranchService.getById(id);
        return ResponseHandler.generateResponse(dto, "Bank branch retrieved successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateBankBranchRequest request) {
        String msg = bankBranchService.create(request);
        return ResponseHandler.generateResponse(msg, HttpStatus.CREATED);
    }
}