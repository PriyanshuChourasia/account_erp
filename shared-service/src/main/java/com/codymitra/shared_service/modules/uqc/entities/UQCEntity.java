package com.codymitra.shared_service.modules.uqc.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "unique_quantity_codes")
@Table(name = "unique_quantity_codes")
@EqualsAndHashCode(callSuper = true)
public class UQCEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    @Size(max = 3,message = "Code has to be only 3 character")
    private String code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;
}
