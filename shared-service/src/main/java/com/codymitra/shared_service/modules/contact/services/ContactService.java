package com.codymitra.shared_service.modules.contact.services;

import com.codymitra.shared_service.modules.contact.dtos.ContactDTO;
import com.codymitra.shared_service.modules.contact.dtos.CreateContactDTO;
import com.codymitra.shared_service.modules.contact.entities.ContactEntity;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    List<ContactDTO> getAllContacts();

    String create(CreateContactDTO createContactDTO);

    ContactEntity show(UUID id);
}
