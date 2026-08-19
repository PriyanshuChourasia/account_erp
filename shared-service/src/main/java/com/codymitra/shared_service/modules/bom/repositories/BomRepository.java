package com.codymitra.shared_service.modules.bom.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.bom.entities.BomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepository extends JpaRepository<BomEntity, UUID> {
}
