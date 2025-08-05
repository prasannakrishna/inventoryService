package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.constant.SkuState;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import lombok.Data;

@Data
public class SkuDocumentDto {

    private Long skuId;
    private String skuName;
    private String productGroup;
    private Double length;
    private Double width;
    private Double height;
    private Double weight;
    private Boolean isHazardous;
    private TrackingLevel trackingLevel;
    private Boolean isSellerSKU;
    private PackConfiguration packingConfiguration;
    private Long sellerId;
    private UnitOfMeasure uomWeight;
    private UnitOfMeasure uomVolume;
    private UnitOfMeasure uomLength;
    private UnitOfMeasure uomDimension;
    private SkuState skuState;
    private TrackingLevel packing_tracking_level;
    private TrackingLevel shipping_tracking_level;
    private TrackingLevel taggedAtLevel;
}