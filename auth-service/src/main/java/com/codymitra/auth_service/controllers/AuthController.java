package com.codymitra.auth_service.controllers;


import com.codymitra.auth_service.dtos.AuthRequestDTO;
import com.codymitra.auth_service.dtos.AuthResponseDTO;
import com.codymitra.auth_service.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String,Object>> authenticate(@Valid @RequestBody AuthRequestDTO authRequestDTO){
        AuthResponseDTO authResponseDTO = authService.
    }

    @PostMapping("/details")
    public ResponseEntity<Map<String,Object>> fetchDetail(HttpServletRequest request){
        String authToken = request.getHeader("Authorization");

        if(authToken == null || !authToken.startsWith("Bearer ")){
            throw new IllegalArgumentException("No Token found");
        }

        String token = authToken.substring(7);


    }
}
