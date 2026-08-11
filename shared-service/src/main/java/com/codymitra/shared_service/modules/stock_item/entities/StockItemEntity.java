package com.codymitra.shared_service.modules.stock_item.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;
import com.codymitra.shared_service.modules.stock_item.enums.StockItemTypeEnum;
import com.codymitra.shared_service.modules.stock_item.enums.TypeOfSupplyEnum;
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


    @Column(name = "unique_quantity_code")
    private String uniqueQuantityCode;

    @Column(name = "stock_item_type")
    private StockItemTypeEnum stockItemType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_group_id")
    private StockGroupEntity stockGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_category_id")
    private StockCategoryEntity stockCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private UnitEntity unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alt_unit_id", nullable = false)
    private UnitEntity altUnitId;

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


    /// HSN if selected GOODS
    @Column(name = "hsn")
    private String hsn;

    /// SAC if selected SERVICES
    @Column(name = "sac")
    private String sac;

    @Column(name = "brand_id")
    private Long brandId;


    /// type of supply goods and services and this will affect gst and taxation
    @Column(name = "type_of_supply")
    private TypeOfSupplyEnum typeOfSupply;



    // all for identification purpose

    ///  identifies as manufacturer/component specific part given by original maker to identify a retail industrial goods
    /// part number is individual component
    @Column(name = "manufacturer_part_no")
    private String manufacturePartNo;

    /// for information, we can say article number or item number is given by business to indetify item
    @Column(name = "article_no")
    private String articleNo;

    ///  this is internal code for product given by company to track its stock keeping unit
    @Column(name = "sku")
    private String sku;



    @Column(name = "active")
    private Boolean active;
}
