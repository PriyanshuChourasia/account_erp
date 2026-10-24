package com.codymitra.shared_service.modules.employee_group.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "employee_groups")
@Table(name = "employee_groups")
@EqualsAndHashCode(callSuper = true)
public class EmployeeGroupEntity extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code", unique = true)
    private String alias;

    @Column(name = "parent_id")
    private UUID parentId;

    // wait for pay heads
//    @Column(name = "active_salary_details")


    @Column(name = "active")
    private Boolean active;
}
