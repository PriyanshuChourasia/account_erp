package com.codymitra.shared_service.modules.account_group.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.account_nature.entities.AccountNatureEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "account_groups")
@Table(name = "account_groups")
@EqualsAndHashCode(callSuper = true)
public class AccountGroupEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "code")
    private Long code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_nature_id")
    private AccountNatureEntity accountNatureId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
