package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.constant.SkuState;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuDTO {

    @NotNull(message = "SKU name is required")
    @Size(min = 1, max = 255)
    private String skuName;

    private String productGroup;

    private Double length;
    private Double width;
    private Double height;
    private Double weight;

    private Boolean isHazardous;

    private TrackingLevel trackingLevel;

    private Boolean isSellerSKU;

    @NotNull(message = "Pack configuration ID is required")
    private Long packConfigId;

    private Long sellerId;

    private UnitOfMeasure uomWeight;
    private UnitOfMeasure uomVolume;
    private UnitOfMeasure uomLength;
    private UnitOfMeasure uomDimension;

    private SkuState skuState;

    private TrackingLevel packing_tracking_level;
    private TrackingLevel shipping_tracking_level;
}

