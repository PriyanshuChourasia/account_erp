package com.codymitra.shared_service.modules.address.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.address.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
