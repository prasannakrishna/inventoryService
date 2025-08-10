package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackConfigurationDto {

    // ID is typically generated, but kept for consistency if used in updates
    private Long id; // Renamed from packConfigId in previous DTO

    @NotBlank(message = "Pack configuration name is mandatory")
    private String packConfigName; // Renamed from packName in previous DTO

    @NotNull(message = "Tag volume is mandatory")
    @Positive(message = "Tag volume must be positive")
    private Double tagVolume;

    @NotNull(message = "Each volume is mandatory")
    @Positive(message = "Each volume must be positive")
    private Double eachVolume;

    @NotNull(message = "Tracking Level 1 is mandatory")
    private TrackingLevel trackingLevel1;

    private TrackingLevel trackingLevel2;

    @NotNull(message = "Tracking ratio 1 to 2 is mandatory")
    @Positive(message = "Tracking ratio 1 to 2 must be positive")
    private Integer trackingRatio1To2;

    private TrackingLevel trackingLevel3;

    private Integer trackingRatio2To3;

    private TrackingLevel trackingLevel4;

    private Integer trackingRatio3To4;

    private TrackingLevel trackingLevel5;

    private Integer trackingRatio4To5;

    @NotNull(message = "Lowest unit weight is mandatory")
    @Positive(message = "Lowest unit weight must be positive")
    private BigDecimal lowestUnitWeight;

    @NotNull(message = "Tag volume weight is mandatory")
    @Positive(message = "Tag volume weight must be positive")
    private BigDecimal tagVolumeWeight;

    private TrackingLevel taggedAtLevel;

    private TrackingLevel handlingUnitLevel;
}