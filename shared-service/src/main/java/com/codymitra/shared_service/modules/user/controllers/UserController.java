package com.codymitra.shared_service.modules.user.controllers;

import com.codymitra.shared_service.modules.user.dtos.CreateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UpdateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UserDTO;
import com.codymitra.shared_service.modules.user.services.UserService;
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
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        String message = userDTOS.size() + " users fetched successfully";
        return ResponseHandler.generateResponse(userDTOS, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable UUID id) {
        UserDTO userDTO = userService.getById(id);
        return ResponseHandler.generateResponse(userDTO, "User fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        String message = userService.create(createUserDTO);
        return ResponseHandler.generateResponse(message, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        String message = userService.update(id, updateUserDTO);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable UUID id) {
        String message = userService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
