package com.bhagwat.scm.inventoryService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryDto {

    // For updates, inventoryId will be passed in the path, but can be here for consistency
    private Long inventoryId;

    @NotNull(message = "SKU ID is required")
    private Long skuId;

    @NotNull(message = "Product ID is required")
    private String productId; // Matches Product entity's String ID

    private Long sellerId;
    private Long storeId;
    private Long ownerId;
    private Long clientId;

    @NotBlank(message = "Location ID is required")
    private String locationId;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Allocated quantity is required")
    @Min(value = 0, message = "Allocated quantity cannot be negative")
    private Integer allocatedQuantity;

    private Double pricePerUnit;
    private String currency; // Send as String, convert to enum in service
    private String unitTrackingLevel; // Send as String, convert to enum in service

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
    private String inventoryType; // Send as String, convert to enum in service
    private String inventoryStatus; // Send as String, convert to enum in service
    private LocalDate expiryDate;
    private ProductVariantDTO productVariantDTO;
}