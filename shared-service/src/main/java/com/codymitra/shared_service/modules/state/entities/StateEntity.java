package com.codymitra.shared_service.modules.state.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "states")
@Table(name = "states")
@EqualsAndHashCode(callSuper = true)
public class StateEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "code", unique = true,nullable = false)
    private String code;

    @Column(name = "gst_code",nullable = false)
    private String gstCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryEntity countryId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
