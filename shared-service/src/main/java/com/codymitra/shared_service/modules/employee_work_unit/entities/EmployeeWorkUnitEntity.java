package com.codymitra.shared_service.modules.employee_work_unit.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.enums.UnitTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "employee_work_units")
@Table(name = "employee_work_units")
@EqualsAndHashCode(callSuper = true)
public class EmployeeWorkUnitEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_type", nullable = false)
    private UnitTypeEnum unitType;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "symbol",nullable = false, unique = true)
    private String symbol;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
