package com.codymitra.shared_service.modules.contact_detail.services;

import com.codymitra.shared_service.modules.contact_detail.dtos.ContactDetailDTO;
import com.codymitra.shared_service.modules.contact_detail.dtos.CreateContactDetailRequest;

import java.util.List;
import java.util.UUID;

public interface ContactDetailService {

    List<ContactDetailDTO> getAll();

    ContactDetailDTO getById(UUID id);

    ContactDetailDTO create(CreateContactDetailRequest request);

    ContactDetailDTO update(UUID id, CreateContactDetailRequest request);

    String delete(UUID id);
}