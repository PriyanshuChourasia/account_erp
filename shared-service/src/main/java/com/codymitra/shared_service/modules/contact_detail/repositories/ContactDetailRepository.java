package com.codymitra.shared_service.modules.contact_detail.repositories;

import com.codymitra.shared_service.modules.contact_detail.entities.ContactDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetailEntity, UUID> {
}