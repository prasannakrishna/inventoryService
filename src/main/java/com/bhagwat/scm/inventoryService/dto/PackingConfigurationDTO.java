package com.bhagwat.scm.inventoryService.dto;

import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingConfigurationDTO {
    private Long id;
    String packConfigName;
    Double tagVolume;
    Double eachVolume;
    TrackingLevel trackingLevel1;
    TrackingLevel trackingLevel2;
    TrackingLevel trackingLevel3;
    Integer trackingLevel1To2Ratio;
    Integer trackingLevel2To3Ratio;
}
