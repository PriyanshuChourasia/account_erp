package com.codymitra.shared_service.modules.contact_detail.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "contact_details")
@Table(name = "contact_details")
@EqualsAndHashCode(callSuper = true)
public class ContactDetailEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "alt_mobile")
    private String altMobile;

    @Column(name = "email")
    private String email;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "address")
    private String address;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_number")
    private String emergencyContactNumber;

    @Column(name = "active")
    private Boolean active;
}