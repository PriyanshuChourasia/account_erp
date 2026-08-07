package com.codymitra.shared_service.modules.country.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "countries")
@Table(name = "countries")
@EqualsAndHashCode(callSuper = true)
public class CountryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "alias", unique = true)
    private String alias;

    @Column(name = "iso2_code")
    private String iso2Code;

    @Column(name = "iso3_code")
    private String iso3Code;

    @Column(name = "numeric_code")
    private String numericCode;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "region")
    private String region;

    @Column(name = "sub_region")
    private String subRegion;


    @Column(name = "active")
    private Boolean active;
}
