package com.bhagwat.scm.inventoryService.entity;
import com.bhagwat.scm.inventoryService.constant.SkuState;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * MongoDB Document representing a denormalized view of an SKU, including
 * its full PackConfiguration details for fast querying.
 */
@Document(collection = "skus")
@Data
public class SKUDocument {

    @Id
    private Long skuId;

    private String skuName;

    private String productGroup;

    private Double length;
    private Double width;
    private Double height;
    private Double weight;

    private Boolean isHazardous;

    private TrackingLevel trackingLevel;

    private Boolean isSellerSKU;

    private PackConfiguration packingConfiguration;

    private Long sellerId; // Store just the ID of the seller here for simplicity

    private UnitOfMeasure uomWeight;
    private UnitOfMeasure uomVolume;
    private UnitOfMeasure uomLength;
    private UnitOfMeasure uomDimension;

    private SkuState skuState;

    private TrackingLevel packing_tracking_level;
    private TrackingLevel shipping_tracking_level;

    // This is a denormalized field from the PackConfiguration,
    // useful for quick filtering without traversing the embedded object
    private TrackingLevel taggedAtLevel;
}