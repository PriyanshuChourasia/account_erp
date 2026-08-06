package com.codymitra.shared_service.modules.journal_reference.repositories;


import com.codymitra.shared_service.modules.journal_reference.entities.JournalReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalReferenceRepository extends JpaRepository<JournalReferenceEntity, Long> {
}
