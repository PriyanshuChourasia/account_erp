package com.codymitra.shared_service.modules.document.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.document.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {
}
