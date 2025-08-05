package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

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
    private String productName;
    private String productBrand;
    private String productDescription;
    private Map<String, String> productHashKeys;
    private boolean productIsSeasonal;
    private double productPrice;
    private double productMRP_Price;
    private boolean productIs_in_stock;
    private String productShippingTrackingLevel;
    private String productFrequency;
    private String productStoreTrackingLevel;
    private boolean productCaptureExpiryDuringCreateInventory;

    // Denormalized SKU Details
    private String skuName;
    private String skuProductGroup;
    private Double skuLength;
    private Double skuWidth;
    private Double skuHeight;
    private Double skuWeight;
    private Boolean skuIsHazardous;
    private String skuTrackingLevel;
    private Boolean skuIsSellerSKU;
    private String skuUomWeight;
    private String skuUomVolume;
    private String skuUomLength;
    private String skuUomDimension;
    private String skuState;
    private String skuPackingTrackingLevel;
    private String skuShippingTrackingLevel;

    // Denormalized PackConfiguration Details (from SKU)
    private PackConfiguration packingConfiguration;
}
