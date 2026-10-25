package com.codymitra.shared_service.modules.document_status.repositories;

import com.codymitra.shared_service.modules.document_status.entities.DocumentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentStatusRepository extends JpaRepository<DocumentStatusEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
