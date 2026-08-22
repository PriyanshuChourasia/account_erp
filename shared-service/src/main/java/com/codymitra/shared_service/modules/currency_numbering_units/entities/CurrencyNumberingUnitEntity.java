package com.codymitra.shared_service.modules.currency_numbering_units.entities;

import java.math.BigDecimal;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "currency_numbering_units")
@Table(name = "currency_numbering_units", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "numbering_system_id"}))
@EqualsAndHashCode(callSuper = true)
public class CurrencyNumberingUnitEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "symbol")
    private String symbol;

    /// numeric value of this unit relative to the base unit, e.g. 100000 for Lakh
    @Column(name = "value", precision = 20, scale = 4)
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numbering_system_id", nullable = false)
    private CurrencyNumberingSystemEntity numberingSystem;

    @Column(name = "sequence",nullable = false)
    private Integer sequence;

    @Column(name = "active")
    private Boolean active;
}
