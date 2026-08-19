package com.codymitra.shared_service.modules.contact.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "contacts")
@Table(name = "contacts")
@EqualsAndHashCode(callSuper = true)
public class ContactEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

}
