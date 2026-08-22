package com.codymitra.shared_service.modules.currency_minor_unit.entities;

import java.math.BigDecimal;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "currency_minor_units")
@Table(name = "currency_minor_units", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "currency_id"}))
@EqualsAndHashCode(callSuper = true)
public class CurrencyMinorUnitEntity extends BaseEntity {

    /// e.g. Paise, Cents, Penny
    @Column(name = "name", nullable = false)
    private String name;

    /// short symbol e.g. p, ¢
    @Column(name = "symbol")
    private String symbol;

    /// how many minor units make one major unit of the currency, e.g. 100
    @Column(name = "value", precision = 20, scale = 4)
    private BigDecimal value;

    @Column(name = "decimal_place")


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private CurrencyEntity currency;

    @Column(name = "active")
    private Boolean active;
}
