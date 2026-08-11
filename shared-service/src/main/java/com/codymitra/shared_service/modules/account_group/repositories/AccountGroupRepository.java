package com.codymitra.shared_service.modules.account_group.repositories;

import com.codymitra.shared_service.modules.account_group.entities.AccountGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountGroupRepository extends JpaRepository<AccountGroupEntity,Long> {
}
