package com.codymitra.shared_service.modules.company.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "companies")
@Table(name = "companies")
@EqualsAndHashCode(callSuper = true)
public class CompanyEntity extends BaseEntity {

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "telephone_no")
    private String telephone_no;

    @Column(name = "mobile_no",nullable = false)
    private String mobile_no;

    @Column(name = "fax_no")
    private String fax_no;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "mailing_name",nullable = false)
    private String mailingName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_currency_id")
    private CurrencyEntity currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numbering_system_id")
    private CurrencyNumberingSystemEntity currencyNumberingSystemEntity;
}
