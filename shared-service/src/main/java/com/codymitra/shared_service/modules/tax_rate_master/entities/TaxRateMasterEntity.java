package com.codymitra.shared_service.modules.tax_rate_master.entities;


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
@Entity(name = "tax_rate_masters")
@Table(name = "tax_rate_masters")
@EqualsAndHashCode(callSuper = true)
public class TaxRateMasterEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "cgst")
    private Double cgst;

    @Column(name = "sgst")
    private Double sgst;

    @Column(name = "igst")
    private Double igst;

    @Column(name = "active")
    private Boolean active;
}
