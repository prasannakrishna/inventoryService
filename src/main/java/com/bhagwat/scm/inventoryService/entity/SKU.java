package com.bhagwat.scm.inventoryService.entity;

import com.bhagwat.scm.inventoryService.constant.SkuState;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import com.bhagwat.scm.inventoryService.converter.UnitOfMeasureConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SKU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skuId;

    @Column(name = "sku_name", nullable = false, length = 255)
    private String skuName;

    private String productGroup;
    @Column(name = "length")
    private Double length;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "is_hazardous", nullable = false)
    private Boolean isHazardous;

    @Enumerated(EnumType.STRING)
    private TrackingLevel trackingLevel;

    @Column(name = "is_seller_sku", nullable = false)
    private Boolean isSellerSKU;

    @ManyToOne
    @JoinColumn(name = "pack_configuration_id", referencedColumnName = "pack_configuration_id", nullable = false)
    private PackConfiguration packingConfiguration;

    @Column(name = "seller_id")
    private String sellerId;


    // Weight field
    @Convert(converter = UnitOfMeasureConverter.class)
    @Column(name = "uom_weight", length = 20)
    private UnitOfMeasure uomWeight;

    public void setUomWeight(UnitOfMeasure uomWeight) {
        if (uomWeight != null && uomWeight.getType() != UnitOfMeasure.UnitType.WEIGHT) {
            throw new IllegalArgumentException("uomWeight must be of type WEIGHT");
        }
        this.uomWeight = uomWeight;
    }

    // Volume field
    @Convert(converter = UnitOfMeasureConverter.class)
    @Column(name = "uom_volume", length = 20)
    private UnitOfMeasure uomVolume;

    public void setUomVolume(UnitOfMeasure uomVolume) {
        if (uomVolume != null && uomVolume.getType() != UnitOfMeasure.UnitType.VOLUME) {
            throw new IllegalArgumentException("uomVolume must be of type VOLUME");
        }
        this.uomVolume = uomVolume;
    }

    // Length field
    @Convert(converter = UnitOfMeasureConverter.class)
    @Column(name = "uom_length", length = 20)
    private UnitOfMeasure uomLength;

    public void setUomLength(UnitOfMeasure uomLength) {
        if (uomLength != null && uomLength.getType() != UnitOfMeasure.UnitType.LENGTH) {
            throw new IllegalArgumentException("uomLength must be of type LENGTH");
        }
        this.uomLength = uomLength;
    }

    // Dimension field (if applicable)
    @Convert(converter = UnitOfMeasureConverter.class)
    @Column(name = "uom_dimension", length = 20)
    private UnitOfMeasure uomDimension;

    public void setUomDimension(UnitOfMeasure uomDimension) {
        if (uomDimension != null && uomDimension.getType() != UnitOfMeasure.UnitType.LENGTH) {
            throw new IllegalArgumentException("uomDimension must be of type LENGTH");
        }
        this.uomDimension = uomDimension;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "sku_state")
    private SkuState skuState;

    @Enumerated(EnumType.STRING)
    @Column(name = "packing_tracking_level")
    private TrackingLevel packing_tracking_level;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_tracking_level")
    private TrackingLevel shipping_tracking_level;

}

