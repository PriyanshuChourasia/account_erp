package com.codymitra.shared_service.modules.user.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.user.enums.UserableTypeEnum;
import com.codymitra.shared_service.modules.user.interfaces.Userable;
import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Email(message = "Invalid email. Please check again")
    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "country_code",nullable = false)
    private String countryCode;

    @Column(name = "license_no",unique = true)
    private String licenseNo;

    @Column(name = "contact_no",nullable = false,unique = true)
    private Long contactNo;

    @Column(name = "alt_contact_no")
    private Long altContactNo;

    @Column(name = "telephone_no")
    private String telePhoneNo;

    @Column(name = "fax_no")
    private String faxNo;

    @Column(name = "website")
    private String website;

    @Column(name = "code", unique = true,nullable = false)
    private String code;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "dob",columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Any(fetch = FetchType.LAZY)
    @AnyDiscriminator(DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = UserableTypeEnum.Values.EMPLOYEE,entity = EmployeeEntity.class)
    @AnyKeyJavaClass(UUID.class)
    @Column(name = "userable_type")
    @JoinColumn(name = "userable_id")
    private Userable userable;

    @Column(name = "userable_id",insertable = false,updatable = false)
    private UUID userableId;

    @Column(name = "userable_type",insertable = false,updatable = false)
    private String userableType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id",nullable = false)
    private UserTypeEntity userType;

    @Column(name = "active")
    private Boolean active;
}
