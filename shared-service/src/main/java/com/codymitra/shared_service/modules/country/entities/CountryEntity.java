package com.codymitra.shared_service.modules.country.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "alias", unique = true)
    private String alias;

    @Column(name = "iso2_code",nullable = false,unique = true)
    @Size(max = 2,message = "Code cannot be greater than 2 character")
    private String iso2Code;

    @Size(max = 3,message = "Code cannot be greater than 3 character")
    @Column(name = "iso3_code",nullable = false,unique = true)
    private String iso3Code;

    @Column(name = "numeric_code",nullable = false,unique = true)
    private String numericCode;

    @Column(name = "phone_code",nullable = false)
    private String phoneCode;

    @Column(name = "currency_code",nullable = false)
    private String currencyCode;

    @Column(name = "currency_name",nullable = false)
    private String currencyName;

    @Column(name = "region")
    private String region;

    @Column(name = "sub_region")
    private String subRegion;


    @Column(name = "active")
    private Boolean active;
}
