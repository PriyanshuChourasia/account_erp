package com.codymitra.shared_service.modules.stock_item.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;
import com.codymitra.shared_service.modules.stock_item.enums.StockItemTypeEnum;
import com.codymitra.shared_service.modules.unit.entities.UnitEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "stock_items")
@Table(name = "stock_items")
@EqualsAndHashCode(callSuper = true)
public class StockItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    /// Unique stock code (e.g., STK0001)
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_group_id")
    private StockGroupEntity stockGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_category_id")
    private StockCategoryEntity stockCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private UnitEntity unit;

    @Column(name = "stock_item_type")
    private StockItemTypeEnum stockItemType;

    /// decimal 5,2
    @Column(name = "gst_rate")
    private Double gstRate;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "opening_quantity")
    private Double openingQuantity;

    @Column(name = "opening_value")
    private Double openingValue;

    @Column(name = "current_quantity")
    private Double currentQuantity;

    @Column(name = "reorder_level")
    private Double reorderLevel;

    /// decimal 12,3
    @Column(name = "minimum_stock", precision = 12, scale = 3)
    private BigDecimal minimumStock;

    @Column(name = "maximum_stock", precision = 12, scale = 3)
    private BigDecimal maximumStock;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "sku")
    private String sku;

    @Column(name = "hsn")
    private String hsn;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "manufacturer_id")
    private Long manufacturerId;

    @Column(name = "status")
    private String status;

    @Column(name = "active")
    private Boolean active;
}
