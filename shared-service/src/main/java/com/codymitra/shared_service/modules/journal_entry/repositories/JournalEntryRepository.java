package com.codymitra.shared_service.modules.journal_entry.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.journal_entry.entities.JournalEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntryEntity, UUID> {
}
