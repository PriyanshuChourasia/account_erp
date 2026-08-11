package com.codymitra.shared_service.modules.country.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/countries")
@RequiredArgsConstructor
public class CountryController {

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllCountries(){
        List<>
    }
}
