package com.codymitra.shared_service.modules.journal.repositories;


import com.codymitra.shared_service.modules.journal.entities.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
}
