package com.codymitra.shared_service.modules.journal_type.repositories;


import com.codymitra.shared_service.modules.journal_type.entities.JournalTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalTypeRepository extends JpaRepository<JournalTypeEntity, Long> {
}
