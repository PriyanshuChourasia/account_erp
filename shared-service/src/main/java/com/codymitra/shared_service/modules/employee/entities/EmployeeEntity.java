package com.codymitra.shared_service.modules.employee.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employee.enums.EmploymentTypeEnum;
import com.codymitra.shared_service.modules.employee.enums.GenderEnum;
import com.codymitra.shared_service.modules.employee_group.entities.EmployeeGroupEntity;
import com.codymitra.shared_service.modules.user.interfaces.Userable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "employees")
@Table(name = "employees")
@EqualsAndHashCode(callSuper = true)
public class EmployeeEntity extends BaseEntity implements Userable {

    @Column(name = "employee_code", nullable = false, unique = true)
    private String employeeCode;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "alt_contact_no")
    private String altContactNo;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "date_of_exit")
    private LocalDate dateOfExit;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentTypeEnum employmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EmployeeStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_group_id")
    private EmployeeGroupEntity employeeGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id")
    private DesignationEntity designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_manager_id")
    private EmployeeEntity reportingManager;

    @Column(name = "pan_no", unique = true)
    private String panNo;

    @Column(name = "aadhaar_no", unique = true)
    private String aadhaarNo;

    @Column(name = "pf_no")
    private String pfNo;

    @Column(name = "esi_no")
    private String esiNo;

    @Column(name = "uan_no")
    private String uanNo;

    @Column(name = "active")
    private Boolean active;
}
