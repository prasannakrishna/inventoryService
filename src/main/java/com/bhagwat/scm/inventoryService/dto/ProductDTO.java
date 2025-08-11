package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.constant.CalendarUnit;
import com.bhagwat.scm.inventoryService.constant.CountFrequency;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.entity.ProductVariant;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Set;

/**
 * DTO for creating or updating a Product.
 * This object is received by the REST controller.
 */
@Data
public class ProductDTO {

    @NotBlank(message = "Product ID is required")
    @Size(min = 1, max = 50)
    private String productId;

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

    // Correctly defined as a Set of Strings to match the entity
    private Set<String> hashKeys;

    private boolean isSeasonal;

    private double price;

    // Renamed to follow standard camelCase convention
    private double mrpPrice;

    // Renamed to follow standard camelCase convention
    private boolean isInStock;

    // Added missing fields from the entity
    private double communityPrice;
    private double retailPrice;

    // Added missing field from the entity, using camelCase
    private Set<CalendarUnit> allowedSubscriptionType;

    // Renamed to follow standard camelCase convention
    private TrackingLevel shippingTrackingLevel;

    private CountFrequency frequency;

    // Renamed to follow standard camelCase convention
    private TrackingLevel storeTrackingLevel;

    // Renamed to follow standard camelCase convention
    private boolean captureExpiryDuringCreateInventory;
    private Set<ProductVariant> variants;

}