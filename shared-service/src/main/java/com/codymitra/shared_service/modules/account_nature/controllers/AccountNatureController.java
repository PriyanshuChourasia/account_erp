package com.codymitra.shared_service.modules.account_nature.controllers;


import com.codymitra.shared_service.modules.account_nature.dtos.AccountNatureDTO;
import com.codymitra.shared_service.modules.account_nature.dtos.CreateAccountNatureDTO;
import com.codymitra.shared_service.modules.account_nature.services.AccountNatureService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/account_natures")
@RequiredArgsConstructor
public class AccountNatureController {

    private final AccountNatureService accountNatureService;


    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllAccountNature(){
        List<AccountNatureDTO> accountNatureDTOS = accountNatureService.getAllAccountNatures();
        String message = accountNatureDTOS.size()+" account nature fetched successfully";
        return ResponseHandler.generateResponse(accountNatureDTOS,message, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createAccountNature(@Valid @RequestBody CreateAccountNatureDTO createAccountNatureDTO){
        String message = accountNatureService.create(createAccountNatureDTO);
        return ResponseHandler.generateResponse(message,HttpStatus.CREATED);
    }

}
