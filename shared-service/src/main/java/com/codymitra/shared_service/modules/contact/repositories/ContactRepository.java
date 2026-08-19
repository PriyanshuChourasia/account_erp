package com.codymitra.shared_service.modules.contact.repositories;

import com.codymitra.shared_service.modules.contact.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, UUID> {
}
