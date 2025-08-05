package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.constant.CountFrequency;
import com.bhagwat.scm.inventoryService.constant.SkuState;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import lombok.Data;

import java.util.Map;

@Data
public class ProductDocumentDto {

    private String productId;
    private String sellerId;

    // Denormalized SKU details
    private Long skuId;
    private String skuName;
    private String skuProductGroup;
    private Double skuLength;
    private Double skuWidth;
    private Double skuHeight;
    private Double skuWeight;
    private Boolean skuIsHazardous;
    private TrackingLevel skuTrackingLevel;
    private Boolean skuIsSellerSKU;
    private Long skuSellerPartyId;
    private UnitOfMeasure skuUomWeight;
    private UnitOfMeasure skuUomVolume;
    private UnitOfMeasure skuUomLength;
    private UnitOfMeasure skuUomDimension;
    private SkuState skuState;
    private TrackingLevel skuPackingTrackingLevel;
    private TrackingLevel skuShippingTrackingLevel;

    // Denormalized PackConfiguration details
    private PackConfiguration packingConfiguration;

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