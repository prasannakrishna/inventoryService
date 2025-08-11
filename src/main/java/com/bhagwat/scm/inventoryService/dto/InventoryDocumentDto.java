package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.entity.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Data
public class InventoryDocumentDto {

    private Long inventoryId;

    private Long skuId;
    private Long productId;
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

    private Instant createdDate;
    private Instant lastModifiedDate;
    private Instant lastStockCheckDate;
    private Instant lastCycleCountDate;

    // Denormalized Product Details
   private Product product;

    // Denormalized SKU Details
   private SKU sku;
   private PackConfiguration packConfiguration;
}
