package com.codymitra.shared_service.modules.account_nature.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.account_nature.entities.AccountNatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountNatureRepository extends JpaRepository<AccountNatureEntity, UUID> {

    Boolean existsByName(String name);
}
