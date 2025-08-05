package com.bhagwat.scm.inventoryService.entity;
import com.bhagwat.scm.inventoryService.constant.CountFrequency;
import com.bhagwat.scm.inventoryService.constant.SkuState;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * MongoDB Document representing a denormalized view of a Product,
 * including its associated SKU and PackConfiguration details for fast querying.
 */
@Document(collection = "products")
@Data
public class ProductDocument {

    @Id
    private String productId; // Matches the String type in Product entity

    private String sellerId;

    // Denormalized SKU details
    private Long skuId; // From SKU entity
    private String skuName; // From SKU entity
    private String skuProductGroup; // From SKU entity
    private Double skuLength; // From SKU entity
    private Double skuWidth; // From SKU entity
    private Double skuHeight; // From SKU entity
    private Double skuWeight; // From SKU entity
    private Boolean skuIsHazardous; // From SKU entity
    private TrackingLevel skuTrackingLevel; // From SKU entity
    private Boolean skuIsSellerSKU; // From SKU entity
    private Long skuSellerPartyId; // From SKU's seller Party entity
    private UnitOfMeasure skuUomWeight; // From SKU entity
    private UnitOfMeasure skuUomVolume; // From SKU entity
    private UnitOfMeasure skuUomLength; // From SKU entity
    private UnitOfMeasure skuUomDimension; // From SKU entity
    private SkuState skuState; // From SKU entity
    private TrackingLevel skuPackingTrackingLevel; // From SKU entity
    private TrackingLevel skuShippingTrackingLevel; // From SKU entity

    // Denormalized PackConfiguration details
    private PackConfiguration packingConfiguration; // Full embedded object

    // Product specific attributes
    private String productName;
    private String brand;
    private String description;
    private Map<String, String> hashKeys;
    private boolean isSeasonal;
    private double price;
    private double mRP_Price;
    private boolean is_in_stock;
    private TrackingLevel shipping_tracking_level;
    private CountFrequency frequency;
    private TrackingLevel store_tracking_level;
    private boolean captureExpiryDuringCreateInventory;
}