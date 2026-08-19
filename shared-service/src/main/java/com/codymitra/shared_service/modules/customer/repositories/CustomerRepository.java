package com.codymitra.shared_service.modules.customer.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.customer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
