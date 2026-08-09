package com.codymitra.shared_service.modules.unit.controllers;


import com.codymitra.shared_service.modules.unit.dtos.CreateUnitRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/units")
@RequiredArgsConstructor
public class UnitController {

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@Valid @RequestBody CreateUnitRequestDTO createUnitRequestDTO){

    }
}
