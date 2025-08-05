package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.constant.CountFrequency;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import lombok.Data;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashMap;

/**
 * DTO for creating or updating a Product.
 * This object is received by the REST controller.
 */
@Data
public class ProductDTO {

    @NotBlank(message = "Product ID is required")
    @Size(min = 1, max = 50)
    private String productId; // Product ID is part of the DTO for create/update

    @NotBlank(message = "Seller ID is required")
    @Size(min = 1, max = 50)
    private String sellerId;

    @NotNull(message = "SKU ID is required")
    private Long skuId;

    @NotBlank(message = "Product name is required")
    @Size(min = 1, max = 255)
    private String productName;

    @Size(max = 100)
    private String brand;

    private String description;

    private Map<String, String> hashKeys = new HashMap<>();

    private boolean isSeasonal;

    private double price;
    private double mRP_Price;

    private boolean is_in_stock;

    private TrackingLevel shipping_tracking_level;

    private CountFrequency frequency;

    private TrackingLevel store_tracking_level;

    private boolean captureExpiryDuringCreateInventory;
}