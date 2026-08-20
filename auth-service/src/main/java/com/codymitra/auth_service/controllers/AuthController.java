package com.codymitra.auth_service.controllers;

import com.codymitra.auth_service.dtos.AuthRequestDTO;
import com.codymitra.auth_service.dtos.AuthResponseDTO;
import com.codymitra.auth_service.dtos.ProfileResponseDTO;
import com.codymitra.auth_service.dtos.UserRegisterDTO;
import com.codymitra.auth_service.services.AuthService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("Came to register");
        AuthResponseDTO authResponseDTO = authService.registerUser(userRegisterDTO);
        log.info("auth response {}",authResponseDTO);
        return ResponseHandler.generateResponse(authResponseDTO, "User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        AuthResponseDTO authResponseDTO = authService.authenticateUser(authRequestDTO);
        return ResponseHandler.generateResponse(authResponseDTO, "Token generated successfully", HttpStatus.OK);
    }

    @PostMapping("/profile")
    public ResponseEntity<Map<String, Object>> fetchDetail(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");

        if (authToken == null || !authToken.startsWith("Bearer ")) {
            throw new IllegalArgumentException("No Token found");
        }

        String token = authToken.substring(7);

        ProfileResponseDTO profileResponseDTO = authService.profile(token);
        return ResponseHandler.generateResponse(profileResponseDTO, "Profile fetched successfully", HttpStatus.OK);
    }
}
