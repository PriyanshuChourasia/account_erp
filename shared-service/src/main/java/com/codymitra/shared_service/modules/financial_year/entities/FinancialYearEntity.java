package com.codymitra.shared_service.modules.financial_year.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "financial_years")
@Table(name = "financial_years")
@EqualsAndHashCode(callSuper = true)
public class FinancialYearEntity extends BaseEntity {

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "start_date",columnDefinition = "DATE",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE",nullable = false)
    private LocalDate endDate;

    @Column(name = "is_current")
    private Boolean isCurrent;
}
