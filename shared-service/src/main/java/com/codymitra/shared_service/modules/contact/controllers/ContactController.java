package com.codymitra.shared_service.modules.contact.controllers;

import com.codymitra.shared_service.modules.contact.dtos.ContactDTO;
import com.codymitra.shared_service.modules.contact.dtos.CreateContactDTO;
import com.codymitra.shared_service.modules.contact.services.ContactService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllContacts(){
        List<ContactDTO> contactDTOS = contactService.getAllContacts();
        String message = contactDTOS.size()+" contacts fetched successfully";
        return ResponseHandler.generateResponse(contactDTOS, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createContact(@Valid @RequestBody CreateContactDTO createContactDTO){
        String message = contactService.create(createContactDTO);
        return ResponseHandler.generateResponse(message, HttpStatus.CREATED);
    }
}
