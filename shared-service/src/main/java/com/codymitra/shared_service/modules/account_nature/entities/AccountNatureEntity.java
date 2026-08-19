package com.codymitra.shared_service.modules.account_nature.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "account_natures")
@Table(name = "account_natures")
@EqualsAndHashCode(callSuper = true)
public class AccountNatureEntity extends BaseEntity {

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "code",unique = true)
    private Integer code;

    @Column(name = "description")
    private String description;

}
