package com.codymitra.shared_service.modules.contact.mappers;

import com.codymitra.shared_service.modules.contact.dtos.ContactDTO;
import com.codymitra.shared_service.modules.contact.dtos.CreateContactDTO;
import com.codymitra.shared_service.modules.contact.entities.ContactEntity;

public final class ContactMapper {

    public static ContactDTO contactDTO(ContactEntity contactEntity){
        return new ContactDTO(
                contactEntity.getId(),
                contactEntity.getName(),
                contactEntity.getPhone(),
                contactEntity.getEmail(),
                contactEntity.getDesignation(),
                contactEntity.getDescription()
        );
    }

    public static ContactEntity contactEntity(CreateContactDTO createContactDTO){
        ContactEntity contact = new ContactEntity();
        contact.setName(createContactDTO.name());
        contact.setPhone(createContactDTO.phone());
        contact.setEmail(createContactDTO.email());
        contact.setDesignation(createContactDTO.designation());
        contact.setDescription(createContactDTO.description());
        return contact;
    }
}
