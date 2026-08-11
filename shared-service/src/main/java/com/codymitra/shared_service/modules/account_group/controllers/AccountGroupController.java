package com.codymitra.shared_service.modules.account_group.controllers;


import com.codymitra.shared_service.modules.account_group.dtos.AccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.dtos.CreateAccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.services.AccountGroupService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/account_groups")
@RequiredArgsConstructor
public class AccountGroupController {

    private final AccountGroupService accountGroupService;

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllAccountGroup(){
        List<AccountGroupDTO> accountGroupDTOS = accountGroupService.getAllAccountGroup();
        String message = accountGroupDTOS.size()+" account groups fetched successfully";
        return ResponseHandler.generateResponse(accountGroupDTOS,message,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@Valid @RequestBody CreateAccountGroupDTO createAccountGroupDTO){
        String message = accountGroupService.create(createAccountGroupDTO);
        return ResponseHandler.generateResponse(message,HttpStatus.CREATED);
    }
}
