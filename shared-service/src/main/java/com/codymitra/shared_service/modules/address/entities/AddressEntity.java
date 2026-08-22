package com.codymitra.shared_service.modules.address.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.address.enums.AddressTypeEnum;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.state.entities.StateEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "addresses")
@Table(name = "addresses")
@EqualsAndHashCode(callSuper = true)
public class AddressEntity extends BaseEntity {

    @Column(name = "address_type",nullable = false)
    private AddressTypeEnum addressType;

    @Column(name = "line_1",nullable = false)
    private String line1;

    @Column(name = "line_2")
    private String line2;

    @Column(name = "city")
    private String city;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "area")
    private String area;

    @Column(name = "po_office")
    private String postOffice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id",nullable = false)
    private StateEntity stateId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id",nullable = false)
    private CountryEntity countryId;

    @Column(name = "pin_code",nullable = false)
    @Size(min = 3, max = 10,message = "PinCode cannot be less than 3 and more than 10")
    private String pinCode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "is_primary",nullable = false)
    private Boolean isPrimary;

    @Column(name = "addressable_id",nullable = false)
    private String addressableId;

    @Column(name = "addressable_type",nullable = false)
    private String addressableType;

    @Column(name = "active",nullable = false)
    private Boolean active;
}
