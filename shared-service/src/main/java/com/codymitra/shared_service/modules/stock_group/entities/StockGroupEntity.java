package com.codymitra.shared_service.modules.stock_group.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "stock_groups")
@Table(name = "stock_groups")
@EqualsAndHashCode(callSuper = true)
public class StockGroupEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "description")
    private String description;

    @Comment("Should add quantities items be added : this reflects in reporting")
    @Column(name = "should_add_quantities",nullable = false)
    private Boolean shouldAddQuantities;

    @Comment("Set/Alter GST Details")
    @Column(name = "set_alter_gst_details",nullable = false)
    private Boolean setAlterGstDetails;

    @Column(name = "active")
    private Boolean active;
}
