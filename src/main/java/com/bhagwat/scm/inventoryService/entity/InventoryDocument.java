package com.bhagwat.scm.inventoryService.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map; // For Product's hashKeys

/**
 * MongoDB Document representing a denormalized view of an Inventory item.
 * It includes attributes from Inventory, and denormalized details from
 * Product, SKU, and PackConfiguration for fast querying.
 */
@Document(collection = "inventory")
@Data
public class InventoryDocument {

    @Id
    private Long inventoryId;

    // --- Core Inventory Attributes ---
    private Long skuId; // ID for direct reference
    private Long productId; // ID for direct reference
    private Long sellerId; // ID for direct reference
    private Long storeId; // ID for direct reference
    private Long ownerId; // ID for direct reference
    private Long clientId; // ID for direct reference

    private String locationId;
    private Integer quantity;
    private Integer allocatedQuantity;
    private Double pricePerUnit;
    private String currency; // Stored as String from enum
    private String unitTrackingLevel; // Stored as String from enum

    // --- Tracking & Traceability Attributes ---
    private String containerId;
    private String shipmentId;
    private String carrierId;
    private String siteId;
    private String orderId;
    private String tagId;
    private String lotNumber;
    private String batchNumber;
    private String serialNumber;
    private String receiptId;
    private String origin;
    private String inventoryCode;
    private String inventoryType; // Stored as String from enum
    private String inventoryStatus; // Stored as String from enum
    private LocalDate expiryDate;

    // --- Auditing Timestamps ---
    private Instant createdDate;
    private Instant lastModifiedDate;
    private Instant lastStockCheckDate;
    private Instant lastCycleCountDate;

    // --- Denormalized Product Details ---
    // Embedding key Product attributes directly for query efficiency
    private String productName;
    private String productBrand;
    private String productDescription;
    private Map<String, String> productHashKeys;
    private boolean productIsSeasonal;
    private double productPrice;
    private double productMRP_Price;
    private boolean productIs_in_stock;
    private String productShippingTrackingLevel; // Stored as String from enum
    private String productFrequency; // Stored as String from enum
    private String productStoreTrackingLevel; // Stored as String from enum
    private boolean productCaptureExpiryDuringCreateInventory;

    // --- Denormalized SKU Details ---
    // Embedding key SKU attributes directly for query efficiency
    private String skuName;
    private String skuProductGroup;
    private Double skuLength;
    private Double skuWidth;
    private Double skuHeight;
    private Double skuWeight;
    private Boolean skuIsHazardous;
    private String skuTrackingLevel; // Stored as String from enum
    private Boolean skuIsSellerSKU;
    private String skuUomWeight; // Stored as String from enum
    private String skuUomVolume; // Stored as String from enum
    private String skuUomLength; // Stored as String from enum
    private String skuUomDimension; // Stored as String from enum
    private String skuState; // Stored as String from enum
    private String skuPackingTrackingLevel; // Stored as String from enum
    private String skuShippingTrackingLevel; // Stored as String from enum

    // --- Denormalized PackConfiguration Details (from SKU) ---
    // Embedding the full PackConfiguration object as it's a nested detail of SKU
    private PackConfiguration packingConfiguration;
}
