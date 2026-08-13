package com.codymitra.shared_service.modules.company_financial_year.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "company_financial_years")
@Table(name = "company_financial_years", uniqueConstraints = {
        @UniqueConstraint(name = "uk_company_financial_year", columnNames = {"company_id", "financial_year_id"})
})
@EqualsAndHashCode(callSuper = true)
public class CompanyFinancialYearEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "financial_year_id", nullable = false)
    private FinancialYearEntity financialYear;

    @Column(name = "book_commencing_from",nullable = false)
    private LocalDate bookCommencingFrom;

    @Column(name = "active")
    private Boolean active;
}
