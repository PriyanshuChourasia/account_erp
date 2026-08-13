package com.codymitra.shared_service.modules.country.repositories;

import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    Boolean existsByName(String name);
}
