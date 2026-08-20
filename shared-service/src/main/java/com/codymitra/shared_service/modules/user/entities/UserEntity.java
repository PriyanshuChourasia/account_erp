package com.codymitra.shared_service.modules.user.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "contact_no",nullable = false,unique = true)
    private Long contactNo;

    @Column(name = "alt_contact_no")
    private Long altContactNo;

    @Column(name = "code", unique = true,nullable = false)
    private String code;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "dob",columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name = "active")
    private Boolean active;
}
