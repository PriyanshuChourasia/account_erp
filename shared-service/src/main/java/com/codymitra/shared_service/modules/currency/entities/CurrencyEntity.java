package com.codymitra.shared_service.modules.currency.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "currencies")
@Table(name = "currencies")
@EqualsAndHashCode(callSuper = true)
public class CurrencyEntity extends BaseEntity {

    @Column(name = "name", nullable = false,unique = true)
    private String name;

    @Column(name = "currency_symbol",nullable = false,unique = true)
    private String currencySymbol;

    @Column(name = "decimal_place",nullable = false)
    private Integer decimalPlace;

    @Column(name = "is_symbol_suffix",nullable = false)
    private Boolean isSymbolSuffix;

    @Column(name = "space_between_amount_and_symbol",nullable = false)
    private Boolean spaceBetweenAmountAndSymbol;

    @Column(name = "decimal_place_printing_amount_to_word")
    private Integer decimalPlacePrintingAmountToWord;
}
