package com.codymitra.shared_service.modules.financial_year.repositories;

import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYearEntity, Long> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);

    List<FinancialYearEntity> findByIsCurrentTrue();

    boolean existsByStartDateAndEndDate(
            LocalDate startDate,
            LocalDate endDate
    );
}
