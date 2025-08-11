package com.bhagwat.scm.inventoryService.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map; // For Product's hashKeys
import java.util.Set;

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
    private Long skuId;
    private String productId;
    private Long sellerId;
    private Long storeId;
    private Long ownerId;
    private Long clientId;

    private String locationId;
    private Integer quantity;
    private Integer allocatedQuantity;
    private Double pricePerUnit;
    private String currency;
    private String unitTrackingLevel;

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
    private String inventoryType;
    private String inventoryStatus;
    private LocalDate expiryDate;

    // --- Auditing Timestamps ---
    private Instant createdDate;
    private Instant lastModifiedDate;
    private Instant lastStockCheckDate;
    private Instant lastCycleCountDate;

    // --- Denormalized Product Details ---
    // Per your request, the full Product object is now embedded.
    private Product product;

    private boolean reserved_for_community_orders;

    private ProductVariant productVariant;
}
