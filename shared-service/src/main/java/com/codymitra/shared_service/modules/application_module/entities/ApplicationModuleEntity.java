package com.codymitra.shared_service.modules.application_module.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "application_modules")
@Table(name = "application_modules")
@EqualsAndHashCode(callSuper = true)
public class ApplicationModuleEntity extends BaseEntity {

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "code",unique = true)
    private Integer code;

    @Column(name = "description")
    private String description;

}
