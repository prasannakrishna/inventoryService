package com.bhagwat.scm.inventoryService.entity;
import com.bhagwat.scm.inventoryService.constant.CountFrequency;
import com.bhagwat.scm.inventoryService.constant.SkuState;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

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

    private SKU sku;

    // Denormalized PackConfiguration details
    private PackConfiguration packingConfiguration; // Full embedded object

    // Product specific attributes
    private String productName;
    private String brand;
    private String description;
    private Set<String> hashKeys;
    private boolean isSeasonal;
    private double price;
    private double mRP_Price;
    private boolean is_in_stock;
    private TrackingLevel shipping_tracking_level;
    private CountFrequency frequency;
    private TrackingLevel store_tracking_level;
    private boolean captureExpiryDuringCreateInventory;
    private double retailPrice;
    private double communityPrice;
}