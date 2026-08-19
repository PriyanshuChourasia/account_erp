package com.codymitra.shared_service.modules.application_feature.entities;


import com.codymitra.shared_service.entities.BaseEntity;
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
    private Integer code;

    @Column(name = "description")
    private String description;

}
