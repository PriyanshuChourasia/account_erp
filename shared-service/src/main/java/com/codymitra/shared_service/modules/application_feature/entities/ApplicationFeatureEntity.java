package com.codymitra.shared_service.modules.application_feature.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.enums.APIDeviceType;
import com.codymitra.shared_service.enums.ApiMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "application_features")
@Table(name = "application_features")
@EqualsAndHashCode(callSuper = true)
public class ApplicationFeatureEntity extends BaseEntity {

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "code",unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_method",nullable = false)
    private ApiMethod apiMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_device_type",nullable = false)
    private APIDeviceType apiDeviceType;

    @Column(name = "end_point",nullable = false)
    private String endPoint;

    @Column(name = "description")
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

}
