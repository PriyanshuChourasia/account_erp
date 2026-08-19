package com.codymitra.shared_service.modules.party.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.account_ledger.interfaces.Ledgerable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "parties")
@Table(name = "parties")
@EqualsAndHashCode(callSuper = true)
public class PartyEntity extends BaseEntity implements Ledgerable {

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
