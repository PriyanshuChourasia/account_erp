package com.codymitra.shared_service.modules.party.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.party.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, UUID> {
}
