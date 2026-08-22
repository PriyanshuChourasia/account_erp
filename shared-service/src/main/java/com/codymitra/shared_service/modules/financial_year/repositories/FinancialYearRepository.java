package com.codymitra.shared_service.modules.financial_year.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYearEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);

    List<FinancialYearEntity> findByIsCurrentTrue();

    boolean existsByStartDateAndEndDate(
            LocalDate startDate,
            LocalDate endDate
    );
}
