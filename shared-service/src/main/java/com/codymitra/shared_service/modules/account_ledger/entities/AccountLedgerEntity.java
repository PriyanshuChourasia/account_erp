package com.codymitra.shared_service.modules.account_ledger.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.account_ledger.interfaces.Ledgerable;
import com.codymitra.shared_service.modules.party.entities.PartyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "account_ledgers")
@Table(name = "account_ledgers")
@EqualsAndHashCode(callSuper = true)
public class AccountLedgerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Any
    @AnyDiscriminator(DiscriminatorType.STRING)
    @AnyDiscriminatorValue(
            discriminator = "PARTY",
            entity = PartyEntity.class
    )
    @AnyKeyJavaClass(Long.class)
    @Column(name = "ledgerable_type")
    @JoinColumn(name = "ledgerable_id")
    private Ledgerable ledgerable;
}
