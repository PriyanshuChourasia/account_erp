package com.codymitra.shared_service.modules.contact.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.contact.dtos.ContactDTO;
import com.codymitra.shared_service.modules.contact.dtos.CreateContactDTO;
import com.codymitra.shared_service.modules.contact.entities.ContactEntity;
import com.codymitra.shared_service.modules.contact.mappers.ContactMapper;
import com.codymitra.shared_service.modules.contact.repositories.ContactRepository;
import com.codymitra.shared_service.modules.contact.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<ContactDTO> getAllContacts(){
        List<ContactEntity> contactEntities = contactRepository.findAll();
        return contactEntities.stream().map(ContactMapper::contactDTO).toList();
    }

    @Override
    public ContactEntity show(UUID id){
        return contactRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Contact does not exists")
        );
    }

    @Override
    public String create(CreateContactDTO createContactDTO){
        contactRepository.save(ContactMapper.contactEntity(createContactDTO));
        return "Contact created successfully";
    }
}
