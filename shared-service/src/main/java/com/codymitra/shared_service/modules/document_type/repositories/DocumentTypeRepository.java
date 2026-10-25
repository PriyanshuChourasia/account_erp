package com.codymitra.shared_service.modules.document_type.repositories;

import com.codymitra.shared_service.modules.document_type.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
